package com.surya432.skripsi.Model;

import java.util.List;

public class ModelListTiket {

    /**
     * status : success
     * data : [{"id":10,"subject":"sdasd","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 22:07:28","updated_at":"2019-07-03 22:07:28","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":9,"subject":"sadasd","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 22:05:52","updated_at":"2019-07-03 22:05:52","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":8,"subject":"sadasd","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 21:59:52","updated_at":"2019-07-03 21:59:52","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":7,"subject":"test1234","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 21:53:42","updated_at":"2019-07-03 21:53:42","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":6,"subject":"test123","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 21:42:46","updated_at":"2019-07-03 21:42:46","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":5,"subject":"test12","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 21:41:24","updated_at":"2019-07-03 21:41:24","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":4,"subject":"test12","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 21:31:50","updated_at":"2019-07-03 21:31:50","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":3,"subject":"ERROR 521 Cloudflare","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 21:27:17","updated_at":"2019-07-03 21:27:17","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":2,"subject":"sadasd","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-03 21:23:44","updated_at":"2019-07-03 21:23:44","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"},{"id":1,"subject":"d","user_id":3,"prioritas_id":3,"status_id":1,"departement_id":2,"rate":null,"created_at":"2019-07-02 20:55:34","updated_at":"2019-07-03 00:25:03","userName":"user1","prioritasName":"Low","departementName":"Support","statusName":"Open"}]
     * total : 10
     */

    private String status;
    private int total;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10
         * subject : sdasd
         * user_id : 3
         * prioritas_id : 3
         * status_id : 1
         * departement_id : 2
         * rate : null
         * created_at : 2019-07-03 22:07:28
         * updated_at : 2019-07-03 22:07:28
         * userName : user1
         * prioritasName : Low
         * departementName : Support
         * statusName : Open
         */

        private int id;
        private String subject;
        private int user_id;
        private int prioritas_id;
        private int status_id;
        private int departement_id;
        private Object rate;
        private String created_at;
        private String updated_at;
        private String userName;
        private String prioritasName;
        private String departementName;
        private String statusName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getPrioritas_id() {
            return prioritas_id;
        }

        public void setPrioritas_id(int prioritas_id) {
            this.prioritas_id = prioritas_id;
        }

        public int getStatus_id() {
            return status_id;
        }

        public void setStatus_id(int status_id) {
            this.status_id = status_id;
        }

        public int getDepartement_id() {
            return departement_id;
        }

        public void setDepartement_id(int departement_id) {
            this.departement_id = departement_id;
        }

        public Object getRate() {
            return rate;
        }

        public void setRate(Object rate) {
            this.rate = rate;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPrioritasName() {
            return prioritasName;
        }

        public void setPrioritasName(String prioritasName) {
            this.prioritasName = prioritasName;
        }

        public String getDepartementName() {
            return departementName;
        }

        public void setDepartementName(String departementName) {
            this.departementName = departementName;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }
}
