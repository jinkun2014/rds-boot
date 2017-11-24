<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${EntityInfo.packageInfo.dao}.${EntityInfo.daoName}">

    <!--配置了autoMapping=true,其他的无需配置，将会自动映射-->
    <resultMap id="${EntityInfo.entityName ? uncap_first}ResultMap" type="${EntityInfo.packageInfo.entity}.${EntityInfo.entityName}" autoMapping="true"/>

    <!--公共sql，其他Mapper可以调用-->
    <sql id="commonFieldSql">
        <choose>
            <when test="fields == null or fields.size() == 0">*</when>
            <otherwise>
                <foreach collection="fields" separator="," item="field">${r'${field}'}</foreach>
            </otherwise>
        </choose>
    </sql>

    <sql id="commonIdsSql">
        <foreach collection="ids" open="WHERE id IN(" item="id" separator="," close=")">${r'#{id}'}</foreach>
    </sql>

    <sql id="select${EntityInfo.entityName}Sql">
        <where>
            <choose>
                <when test="${EntityInfo.entityName ? uncap_first}==null"></when>
                <otherwise>
                <#list EntityInfo.fieldInfoList as fieldInfo>
                    <if test="${EntityInfo.entityName ? uncap_first}.${fieldInfo.name}!=null">AND ${fieldInfo.columnName}=${r'#{'}${EntityInfo.entityName ? uncap_first}.${fieldInfo.name}${r'}'}</if>
                </#list>
                </otherwise>
            </choose>
        </where>
    </sql>

    <insert id="insert" useGeneratedKeys="true" keyProperty="${EntityInfo.entityName ? uncap_first}.${EntityInfo.primaryKey.name}">
        INSERT INTO ${EntityInfo.tableName}
        (
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo_has_next>
        ${fieldInfo.columnName},
        <#else>
        ${fieldInfo.columnName}
        </#if>
    </#list>
        )
        VALUES
        (
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if fieldInfo_has_next>
        ${r'#{'}${EntityInfo.entityName ? uncap_first}.${fieldInfo.name}${r'}'},
        <#else>
        ${r'#{'}${EntityInfo.entityName ? uncap_first}.${fieldInfo.name}${r'}'}
        </#if>
    </#list>
        )
    </insert>

    <update id="update">
        UPDATE ${EntityInfo.tableName}
        <set>
<#list EntityInfo.fieldInfoList as fieldInfo>
    <#if !fieldInfo.primaryKey>
        <#if fieldInfo_has_next>
            <if test="${EntityInfo.entityName ? uncap_first}.${fieldInfo.name} != null">
            ${fieldInfo.columnName} = ${r'#{'}${EntityInfo.entityName ? uncap_first}.${fieldInfo.name}${r'}'},
            </if>
        <#else>
            <if test="${EntityInfo.entityName ? uncap_first}.${fieldInfo.name} != null">
            ${fieldInfo.columnName} = ${r'#{'}${EntityInfo.entityName ? uncap_first}.${fieldInfo.name}${r'}'}
            </if>
        </#if>
    </#if>
</#list>
        </set>
        WHERE ${EntityInfo.primaryKey.columnName} = ${r'#{'}${EntityInfo.entityName ? uncap_first}.${EntityInfo.primaryKey.name}${r'}'}
    </update>

    <delete id="delete">
        DELETE
        FROM ${EntityInfo.tableName}
        <include refid="select${EntityInfo.entityName}Sql"/>
    </delete>

    <delete id="deleteByIds">
        DELETE
        FROM ${EntityInfo.tableName}
        <include refid="commonIdsSql"/>
    </delete>

    <select id="loadCount" resultType="int">
        SELECT COUNT(id)
        FROM ${EntityInfo.tableName}
        <include refid="select${EntityInfo.entityName}Sql"/>
    </select>

    <select id="loadByPK" resultMap="${EntityInfo.entityName ? uncap_first}ResultMap">
        SELECT
        <include refid="commonFieldSql"/>
        FROM ${EntityInfo.tableName}
        WHERE id = ${r'#{id}'}
    </select>

    <select id="loads" resultMap="${EntityInfo.entityName ? uncap_first}ResultMap">
        SELECT
        <include refid="commonFieldSql"/>
        FROM ${EntityInfo.tableName}
        <include refid="select${EntityInfo.entityName}Sql"/>
    </select>

</mapper>