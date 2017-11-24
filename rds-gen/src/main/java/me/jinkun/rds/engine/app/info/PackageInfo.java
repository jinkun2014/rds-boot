package me.jinkun.rds.engine.app.info;

/**
 * @Description: 包相关信息 <br/>
 * @Autor: Created by jinkun on 2016/12/6.
 */
public class PackageInfo {
    public String base;
    public String utils;
    public String page;
    public String resp;
    public String module;
    public String entity;
    public String dao;
    public String daoImpl;
    public String service;
    public String serviceImpl;
    public String web;
    public String form;
    public String formBase;
    public String result;
    public String resultBase;
    public String convert;
    public String convertBase;
    public String mapper;

    public PackageInfo() {
    }

    public PackageInfo(String base, String module) {
        this.base = base;
        this.utils = base + ".core.utils";
        this.page = base + ".core.page";
        this.resp = base + ".core.resp";
        this.module = module;
        this.entity = base + "." + module + ".domain";
        this.dao = base + "." + module + ".dao";
        this.daoImpl = base + "." + module + ".dao.impl";
        this.service = base + "." + module + ".service";
        this.serviceImpl = base + "." + module + ".service.impl";
        this.web = base + "." + module + ".web";
        this.form = this.web + ".form";
        this.result = this.web + ".result";
        this.formBase = base + ".core" + ".base";
        this.resultBase = base + ".core" + ".base";
        this.convertBase = base + ".core" + ".base";
        this.convert = base + "." + module + ".convert";
        this.mapper = "mapper";
    }

    public PackageInfo(Builder builder) {
        this.base = builder.base;
        this.utils = builder.utils;
        this.page = builder.page;
        this.resp = builder.resp;
        this.module = builder.module;
        this.entity = builder.entity;
        this.dao = builder.dao;
        this.daoImpl = builder.daoImpl;
        this.service = builder.service;
        this.serviceImpl = builder.serviceImpl;
        this.web = builder.web;
        this.form = builder.form;
        this.result = builder.result;
        this.formBase = builder.formBase;
        this.resultBase = builder.resultBase;
        this.convertBase = builder.convertBase;
        this.convert = builder.convert;
        this.mapper = builder.mapper;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getDao() {
        return dao;
    }

    public void setDao(String dao) {
        this.dao = dao;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getDaoImpl() {
        return daoImpl;
    }

    public void setDaoImpl(String daoImpl) {
        this.daoImpl = daoImpl;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getConvert() {
        return convert;
    }

    public void setConvert(String convert) {
        this.convert = convert;
    }

    public String getFormBase() {
        return formBase;
    }

    public void setFormBase(String formBase) {
        this.formBase = formBase;
    }

    public String getResultBase() {
        return resultBase;
    }

    public void setResultBase(String resultBase) {
        this.resultBase = resultBase;
    }

    public String getConvertBase() {
        return convertBase;
    }

    public void setConvertBase(String convertBase) {
        this.convertBase = convertBase;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getUtils() {
        return utils;
    }

    public void setUtils(String utils) {
        this.utils = utils;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public static class Builder {
        private String base;
        private String utils;
        private String page;
        private String resp;
        private String module;
        private String entity;
        private String dao;
        private String daoImpl;
        private String service;
        private String serviceImpl;
        private String web;
        private String form;
        private String formBase;
        private String result;
        private String resultBase;
        private String convert;
        private String convertBase;
        private String mapper;

        public Builder base(String base) {
            this.base = base;
            return this;
        }

        public Builder utils(String utils) {
            this.utils = utils;
            return this;
        }

        public Builder page(String page) {
            this.page = page;
            return this;
        }

        public Builder resp(String resp) {
            this.resp = resp;
            return this;
        }

        public Builder module(String module) {
            this.module = module;
            return this;
        }

        public Builder entity(String entity) {
            this.entity = entity;
            return this;
        }

        public Builder dao(String dao) {
            this.dao = dao;
            return this;
        }

        public Builder daoImpl(String daoImpl) {
            this.daoImpl = daoImpl;
            return this;
        }

        public Builder service(String service) {
            this.service = service;
            return this;
        }

        public Builder serviceImpl(String serviceImpl) {
            this.serviceImpl = serviceImpl;
            return this;
        }

        public Builder web(String web) {
            this.web = web;
            return this;
        }

        public Builder form(String form) {
            this.form = form;
            return this;
        }

        public Builder formBase(String formBase) {
            this.formBase = formBase;
            return this;
        }

        public Builder result(String result) {
            this.result = result;
            return this;
        }

        public Builder resultBase(String resultBase) {
            this.resultBase = resultBase;
            return this;
        }

        public Builder convert(String convert) {
            this.convert = convert;
            return this;
        }

        public Builder convertBase(String convertBase) {
            this.convertBase = convertBase;
            return this;
        }

        public Builder mapper(String mapper) {
            this.mapper = mapper;
            return this;
        }

        public PackageInfo builder() {
            return new PackageInfo(this);
        }
    }
}
