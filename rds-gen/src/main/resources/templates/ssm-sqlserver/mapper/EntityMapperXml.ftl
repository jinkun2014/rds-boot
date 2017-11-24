<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${EntityInfo.packageInfo.dao}.${EntityInfo.entityName}Dao">
    <resultMap id="BaseResultMap" type="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}">
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if fieldInfo.primaryKey>
        <id column="${fieldInfo.columnName}" property="${fieldInfo.name}"/>
    <#else>
        <result column="${fieldInfo.columnName}" property="${fieldInfo.name}"/>
    </#if>
</#list>
    </resultMap>
    <sql id="Base_Column_List">
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo_has_next>
        ${fieldInfo.columnName} as ${fieldInfo.name},
        <#else>
        ${fieldInfo.columnName} as ${fieldInfo.name}
        </#if>
    </#list>
    </sql>
    <insert id="insert" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}" useGeneratedKeys="true" keyProperty="id">
        <selectKey keyProperty="id" resultType="${EntityInfo.primaryKey.type}" order="BEFORE">
            SELECT LOWER(REPLACE(LTRIM(NEWID()),'-',''))
        </selectKey>
        insert into ${EntityInfo.tableName}
        (
        <#list EntityInfo.fieldInfoList as fieldInfo>
            <#if fieldInfo_has_next>
            ${fieldInfo.columnName},
            <#else>
            ${fieldInfo.columnName}
            </#if>
        </#list>
        )
        values
        (
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo_has_next>
            ${r'#{'}${fieldInfo.name}${r'}'},
        <#else>
            ${r'#{'}${fieldInfo.name}${r'}'}
        </#if>
    </#list>
        )
    </insert>

<#if EntityInfo.primaryKey??>
    <update id="update" parameterType="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}">
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

    <delete id="delete" parameterType="java.lang.${EntityInfo.primaryKey.type}">
        delete from ${EntityInfo.tableName}
        where ${EntityInfo.primaryKey.columnName} = ${r'#{'}${EntityInfo.primaryKey.name}${r'}'}
    </delete>

    <select id="selectById" parameterType="java.lang.${EntityInfo.primaryKey.type}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${EntityInfo.tableName}
        where ${EntityInfo.primaryKey.columnName} = ${r'#{'}${EntityInfo.primaryKey.name}${r'}'}
    </select>

    <select id="query" resultType="hashmap">
        select
        row_number () OVER (ORDER BY id DESC) AS rownum,
        <include refid="Base_Column_List"/>
        from ${EntityInfo.tableName}
        where 1=1
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if !fieldInfo.primaryKey>
        <if test="conds.${fieldInfo.name} != null">
            and ${fieldInfo.columnName} = ${r'#{conds.'}${fieldInfo.name}${r'}'}
        </if>
    </#if>
</#list>
    </select>

    <select id="findAllByParams" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${EntityInfo.tableName}
        where 1=1
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if !fieldInfo.primaryKey>
        <if test="${fieldInfo.name} != null">
            and ${fieldInfo.columnName} = ${r'#{'}${fieldInfo.name}${r'}'}
        </if>
    </#if>
</#list>
    </select>

    <select id="findByParams" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${EntityInfo.tableName}
        where 1=1
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if !fieldInfo.primaryKey>
        <if test="${fieldInfo.name} != null">
            and ${fieldInfo.columnName} = ${r'#{'}${fieldInfo.name}${r'}'}
        </if>
    </#if>
</#list>
    </select>
</#if>
</mapper>