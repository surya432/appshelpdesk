package com.surya432.skripsi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ModelArtikel implements Parcelable {

    public static final Parcelable.Creator<ModelArtikel> CREATOR = new Parcelable.Creator<ModelArtikel>() {
        @Override
        public ModelArtikel createFromParcel(Parcel source) {
            return new ModelArtikel(source);
        }

        @Override
        public ModelArtikel[] newArray(int size) {
            return new ModelArtikel[size];
        }
    };
    /**
     * status : success
     * data : [{"id":2,"name":"CLOUDFRARE","body":"<p>asd21323<\/p>","created_by":"Surya Hadi Prastya","departement_id":1,"created_at":"2019-07-22 16:01:28","updated_at":"2019-07-22 16:12:52"},{"id":3,"name":"CLOUDFRARE DRAKORBOO232","body":"<p>asd2132323232<\/p>","created_by":"Surya Hadi Prastya","departement_id":1,"created_at":"2019-07-22 16:10:16","updated_at":"2019-07-22 16:10:16"},{"id":4,"name":"sds","body":"<p>asdasd<\/p>","created_by":"Surya Hadi Prastya","departement_id":1,"created_at":"2019-07-23 00:11:09","updated_at":"2019-07-23 00:11:09"},{"id":5,"name":"haho","body":"<p>2232<\/p>","created_by":"Surya Hadi Prastya","departement_id":1,"created_at":"2019-07-23 00:11:28","updated_at":"2019-07-23 00:11:28"}]
     */

    private String status;
    private List<DataBean> data;

    public ModelArtikel() {
    }

    protected ModelArtikel(Parcel in) {
        this.status = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeTypedList(this.data);
    }

    public static class DataBean implements Parcelable {
        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
        /**
         * id : 2
         * name : CLOUDFRARE
         * body : <p>asd21323</p>
         * created_by : Surya Hadi Prastya
         * departement_id : 1
         * created_at : 2019-07-22 16:01:28
         * updated_at : 2019-07-22 16:12:52
         */

        private int id;
        private String name;
        private String body;
        private String created_by;
        private int departement_id;
        private String created_at;
        private String updated_at;

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.body = in.readString();
            this.created_by = in.readString();
            this.departement_id = in.readInt();
            this.created_at = in.readString();
            this.updated_at = in.readString();
        }

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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public int getDepartement_id() {
            return departement_id;
        }

        public void setDepartement_id(int departement_id) {
            this.departement_id = departement_id;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.body);
            dest.writeString(this.created_by);
            dest.writeInt(this.departement_id);
            dest.writeString(this.created_at);
            dest.writeString(this.updated_at);
        }
    }
}
