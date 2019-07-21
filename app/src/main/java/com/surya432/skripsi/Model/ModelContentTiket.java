package com.surya432.skripsi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ModelContentTiket {

    /**
     * status : success
     * data : [{"id":43,"body":"hajja","tiket_id":36,"senders":"user1","repply":0,"created_at":"2019-07-19 02:24:25","updated_at":"2019-07-19 02:24:25","attachment_file":[{"id":27,"name":"4345763b302e2312cde5dfcab8aee64dIMG-20190718-WA0037.jpg","file":"attachment/4345763b302e2312cde5dfcab8aee64dIMG-20190718-WA0037.jpg","mime":"image/jpeg","content_tiket_id":43,"created_at":"2019-07-19 02:24:25","updated_at":"2019-07-19 02:24:25"}]}]
     */

    private String status;
    private List<DataBean> data;

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

    public static class DataBean implements Parcelable {
        /**
         * id : 43
         * body : hajja
         * tiket_id : 36
         * senders : user1
         * repply : 0
         * created_at : 2019-07-19 02:24:25
         * updated_at : 2019-07-19 02:24:25
         * attachment_file : [{"id":27,"name":"4345763b302e2312cde5dfcab8aee64dIMG-20190718-WA0037.jpg","file":"attachment/4345763b302e2312cde5dfcab8aee64dIMG-20190718-WA0037.jpg","mime":"image/jpeg","content_tiket_id":43,"created_at":"2019-07-19 02:24:25","updated_at":"2019-07-19 02:24:25"}]
         */

        private int id;
        private String body;
        private int tiket_id;
        private String senders;
        private int repply;
        private String created_at;
        private String updated_at;
        private List<AttachmentFileBean> attachment_file;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getTiket_id() {
            return tiket_id;
        }

        public void setTiket_id(int tiket_id) {
            this.tiket_id = tiket_id;
        }

        public String getSenders() {
            return senders;
        }

        public void setSenders(String senders) {
            this.senders = senders;
        }

        public int getRepply() {
            return repply;
        }

        public void setRepply(int repply) {
            this.repply = repply;
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

        public List<AttachmentFileBean> getAttachment_file() {
            return attachment_file;
        }

        public void setAttachment_file(List<AttachmentFileBean> attachment_file) {
            this.attachment_file = attachment_file;
        }

        public static class AttachmentFileBean implements Parcelable {
            /**
             * id : 27
             * name : 4345763b302e2312cde5dfcab8aee64dIMG-20190718-WA0037.jpg
             * file : attachment/4345763b302e2312cde5dfcab8aee64dIMG-20190718-WA0037.jpg
             * mime : image/jpeg
             * content_tiket_id : 43
             * created_at : 2019-07-19 02:24:25
             * updated_at : 2019-07-19 02:24:25
             */

            private int id;
            private String name;
            private String file;
            private String mime;
            private int content_tiket_id;
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

            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;
            }

            public String getMime() {
                return mime;
            }

            public void setMime(String mime) {
                this.mime = mime;
            }

            public int getContent_tiket_id() {
                return content_tiket_id;
            }

            public void setContent_tiket_id(int content_tiket_id) {
                this.content_tiket_id = content_tiket_id;
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
                dest.writeString(this.file);
                dest.writeString(this.mime);
                dest.writeInt(this.content_tiket_id);
                dest.writeString(this.created_at);
                dest.writeString(this.updated_at);
            }

            public AttachmentFileBean() {
            }

            protected AttachmentFileBean(Parcel in) {
                this.id = in.readInt();
                this.name = in.readString();
                this.file = in.readString();
                this.mime = in.readString();
                this.content_tiket_id = in.readInt();
                this.created_at = in.readString();
                this.updated_at = in.readString();
            }

            public static final Creator<AttachmentFileBean> CREATOR = new Creator<AttachmentFileBean>() {
                @Override
                public AttachmentFileBean createFromParcel(Parcel source) {
                    return new AttachmentFileBean(source);
                }

                @Override
                public AttachmentFileBean[] newArray(int size) {
                    return new AttachmentFileBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.body);
            dest.writeInt(this.tiket_id);
            dest.writeString(this.senders);
            dest.writeInt(this.repply);
            dest.writeString(this.created_at);
            dest.writeString(this.updated_at);
            dest.writeList(this.attachment_file);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.body = in.readString();
            this.tiket_id = in.readInt();
            this.senders = in.readString();
            this.repply = in.readInt();
            this.created_at = in.readString();
            this.updated_at = in.readString();
            this.attachment_file = new ArrayList<AttachmentFileBean>();
            in.readList(this.attachment_file, AttachmentFileBean.class.getClassLoader());
        }

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
    }
}
