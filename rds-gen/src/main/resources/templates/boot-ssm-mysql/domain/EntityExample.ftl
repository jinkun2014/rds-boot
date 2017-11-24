<#-- package -->
package ${EntityInfo.packageInfo.entity};

import java.util.ArrayList;
import java.util.List;
<#-- import -->
<#list EntityInfo.importList as import>
import ${import};
</#list>


<#-- 注释 -->
/**
 * @Description: ${EntityInfo.remarks} <br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
public class ${EntityInfo.entityName}Example {<#-- 类名 -->

    protected String orderByClause;

    protected boolean distinct;

    protected Long start = 0L;

    protected Long size = 10L;

    protected List<Criteria> oredCriteria;

    public ${EntityInfo.entityName}Example() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getStart() {
        return this.start;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getSize() {
        return this.size;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }
<#-- 属性 -->
<#list EntityInfo.fieldInfoList as fieldInfo>

        public Criteria and${fieldInfo.name ? cap_first}IsNull() {
            addCriterion("${fieldInfo.columnName} is null");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}IsNotNull() {
            addCriterion("${fieldInfo.columnName} is not null");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}EqualTo(${fieldInfo.type} value) {
            addCriterion("${fieldInfo.columnName} =", value, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}NotEqualTo(${fieldInfo.type} value) {
            addCriterion("${fieldInfo.columnName} <>", value, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}GreaterThan(${fieldInfo.type} value) {
            addCriterion("${fieldInfo.columnName} >", value, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}GreaterThanOrEqualTo(${fieldInfo.type} value) {
            addCriterion("${fieldInfo.columnName} >=", value, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}LessThan(${fieldInfo.type} value) {
            addCriterion("${fieldInfo.columnName} <", value, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}LessThanOrEqualTo(${fieldInfo.type} value) {
            addCriterion("${fieldInfo.columnName} <=", value, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}Like(${fieldInfo.type} value) {
            addCriterion("${fieldInfo.columnName} like", value, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}NotLike(${fieldInfo.type} value) {
            addCriterion("${fieldInfo.columnName} not like", value, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}In(List<${fieldInfo.type}> values) {
            addCriterion("${fieldInfo.columnName} in", values, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}NotIn(List<${fieldInfo.type}> values) {
            addCriterion("${fieldInfo.columnName} not in", values, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}Between(${fieldInfo.type} value1, ${fieldInfo.type} value2) {
            addCriterion("${fieldInfo.columnName} between", value1, value2, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

        public Criteria and${fieldInfo.name ? cap_first}NotBetween(${fieldInfo.type} value1, ${fieldInfo.type} value2) {
            addCriterion("${fieldInfo.columnName} not between", value1, value2, "${fieldInfo.columnName}");
            return (Criteria) this;
        }

</#list>
    }
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}