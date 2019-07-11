package com.surya432.skripsi.Model;

import java.util.List;

public class ModelStatus {

    /**
     * status : success
     * data : [{"id":1,"name":"Low","created_at":"2019-06-30 20:19:26","updated_at":"2019-06-30 20:19:26"},{"id":3,"name":"Medium","created_at":"2019-06-30 21:18:49","updated_at":"2019-06-30 21:18:49"},{"id":4,"name":"High","created_at":"2019-06-30 21:20:47","updated_at":"2019-06-30 21:20:47"}]
     */

    private String status;
    private List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * id : 1
         * name : Low
         * created_at : 2019-06-30 20:19:26
         * updated_at : 2019-06-30 20:19:26
         */

        private int id;
        private String name;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
