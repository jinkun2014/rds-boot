<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${EntityInfo.packageInfo.dao}.${EntityInfo.entityName}Mapper">
    <resultMap id="BaseResultMap" type="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}">
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if fieldInfo.primaryKey>
        <id column="${fieldInfo.columnName}" property="${fieldInfo.name}"/>
    <#else>
        <result column="${fieldInfo.columnName}" property="${fieldInfo.name}"/>
    </#if>
</#list>
    </resultMap>
    <sql id="Example_Where_Clause">
        <where>
            <foreach collection="oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" prefixOverrides="and" suffix=")">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${r'${criterion.condition}'}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${r'${criterion.condition}'} ${r'#{criterion.value}'}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${r'${criterion.condition}'} ${r'#{criterion.value}'} and ${r'#{criterion.secondValue}'}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r'${criterion.condition}'}
                                    <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                                    ${r'#{listItem}'}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    <sql id="Update_By_Example_Where_Clause">
        <where>
            <foreach collection="example.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" prefixOverrides="and" suffix=")">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${r'${criterion.condition}'}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${r'${criterion.condition}'} ${r'#{criterion.value}'}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${r'${criterion.condition}'} ${r'#{criterion.value}'} and ${r'#{criterion.secondValue}'}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r'${criterion.condition}'}
                                    <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                                    ${r'#{listItem}'}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    <sql id="Base_Column_List">
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo_has_next>
        ${fieldInfo.columnName},
        <#else>
        ${fieldInfo.columnName}
        </#if>
    </#list>
    </sql>
    <select id="selectByExample" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}Example" resultMap="BaseResultMap">
        select
        <if test="distinct">
            distinct
        </if>
        <include refid="Base_Column_List"/>
        from ${EntityInfo.tableName}
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
        <if test="orderByClause != null">
            order by ${r'${orderByClause}'}
        </if>
    </select>
<#-- 如果没有主键就没有以下方法 -->
<#if EntityInfo.primaryKey??>
    <select id="selectByPrimaryKey" parameterType="java.lang.${EntityInfo.primaryKey.type}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${EntityInfo.tableName}
        where ${EntityInfo.primaryKey.columnName} = ${r'#{'}${EntityInfo.primaryKey.name}${r'}'}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.${EntityInfo.primaryKey.type}">
        delete from ${EntityInfo.tableName}
        where ${EntityInfo.primaryKey.columnName} = ${r'#{'}${EntityInfo.primaryKey.name}${r'}'}
    </delete>
</#if>
    <delete id="deleteByExample" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}Example">
        delete from ${EntityInfo.tableName}
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </delete>
    <insert id="insert" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}">
        insert into ${EntityInfo.tableName} (<include refid="Base_Column_List"/>)
        values (
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo_has_next>
            ${r'#{'}${fieldInfo.name}${r'}'},
        <#else>
            ${r'#{'}${fieldInfo.name}${r'}'}
        </#if>
    </#list>
        )
    </insert>
    <insert id="insertSelective" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}">
        insert into ${EntityInfo.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list EntityInfo.fieldInfoList as fieldInfo>
            <if test="${fieldInfo.name} != null">
                ${fieldInfo.columnName},
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list EntityInfo.fieldInfoList as fieldInfo>
            <if test="${fieldInfo.name} != null">
                ${r'#{'}${fieldInfo.name}${r'}'},
            </if>
        </#list>
        </trim>
    </insert>
    <select id="countByExample" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}Example" resultType="java.lang.Long">
        select count(*) from ${EntityInfo.tableName}
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </select>
    <update id="updateByExampleSelective" parameterType="map">
        update ${EntityInfo.tableName}
        <set>
        <#list EntityInfo.fieldInfoList as fieldInfo>
            <if test="record.${fieldInfo.name} != null">
                ${fieldInfo.columnName} = ${r'#{'}record.${fieldInfo.name}${r'}'},
            </if>
        </#list>
        </set>
        <if test="_parameter != null">
            <include refid="Update_By_Example_Where_Clause"/>
        </if>
    </update>
    <update id="updateByExample" parameterType="map">
        update ${EntityInfo.tableName}
        set
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo_has_next>
            ${fieldInfo.columnName} = ${r'#{record.'}${fieldInfo.name}${r'}'},
        <#else >
            ${fieldInfo.columnName} = ${r'#{record.'}${fieldInfo.name}${r'}'}
        </#if>
    </#list>
        <if test="_parameter != null">
            <include refid="Update_By_Example_Where_Clause"/>
        </if>
    </update>
<#-- 如果没有主键就没有以下方法 -->
<#if EntityInfo.primaryKey??>
    <update id="updateByPrimaryKeySelective" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}">
        update ${EntityInfo.tableName}
        <set>
        <#list EntityInfo.fieldInfoList as fieldInfo>
            <#if !fieldInfo.primaryKey>
                <#if fieldInfo_has_next>
            <if test="${fieldInfo.name} != null">
                ${fieldInfo.columnName} = ${r'#{'}${fieldInfo.name}${r'}'},
            </if>
                <#else>
            <if test="${fieldInfo.name} != null">
                ${fieldInfo.columnName} = ${r'#{'}${fieldInfo.name}${r'}'}
            </if>
                </#if>
            </#if>
        </#list>
        </set>
        where ${EntityInfo.primaryKey.columnName} = ${r'#{'}${EntityInfo.primaryKey.name}${r'}'}
    </update>
    <update id="updateByPrimaryKey" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}">
        update ${EntityInfo.tableName}
        set
        <#list EntityInfo.fieldInfoList as fieldInfo>
            <#if !fieldInfo.primaryKey>
                <#if fieldInfo_has_next>
            ${fieldInfo.columnName} = ${r'#{'}${fieldInfo.name}${r'}'},
                <#else>
            ${fieldInfo.columnName} = ${r'#{'}${fieldInfo.name}${r'}'}
                </#if>
            </#if>
        </#list>
        where ${EntityInfo.primaryKey.columnName} = ${r'#{'}${EntityInfo.primaryKey.name}${r'}'}
    </update>
</#if>
    <select id="selectPageByExample" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}Example" resultMap="BaseResultMap">
        select
        <if test="distinct">
            distinct
        </if>
        <include refid="Base_Column_List"/>
        from ${EntityInfo.tableName}
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
        <if test="orderByClause != null">
            order by ${r'${orderByClause}'}
        </if>
        limit ${r'#{start}'},${r'#{size}'};
    </select>

    <!--===================== 以下是新增方法 =======================-->

</mapper>