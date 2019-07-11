package com.surya432.skripsi.Model;

public class ModelLogin {

    /**
     * status : success
     * error :
     * data : {"id":3,"name":"user1","email":"user1@test.com","role":"User","avatar":{"encoded":"data:image/png;base64,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","mime":"image/png","dirname":null,"basename":null,"extension":null,"filename":null},"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImZjODU2MDcxNWUyMGRmZjBjNjVjZTIwZWMzZjZhYmI0ZGY3YjJjOTdkNzc0ZDhmNzFjZDExNjNkYWQ0NmI3ZTMzMzI3ZTJjNmQxYzc0YTQ1In0.eyJhdWQiOiIxIiwianRpIjoiZmM4NTYwNzE1ZTIwZGZmMGM2NWNlMjBlYzNmNmFiYjRkZjdiMmM5N2Q3NzRkOGY3MWNkMTE2M2RhZDQ2YjdlMzMzMjdlMmM2ZDFjNzRhNDUiLCJpYXQiOjE1NjI1MDc5NzMsIm5iZiI6MTU2MjUwNzk3MywiZXhwIjoxNTk0MTMwMzczLCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.LMefg-FNPlm7j_DSjm0IbCQYE8L6RTrAlj87v3vNml7jChmBkoWHGSl-fU9l2XSwT_1LtOWc6pJmFe5A6N26uyA4qXf5UtQ3ZrRoDCzAWVYgtdxzoE1YBvwHP0SVr_EEMUZ8mRVQLQfXaX83xEzWpdnL2KDM65GfWAEyEaPuqSe_qPXEaabCebKW0g_naFJAGnVD1tr0HAyOhryttt0p-5rY1afiLqxyQtDLnzuCakBESTm5AGG9eG1Xqo-bdn5zDXML5yuUVbrZItLkmYSI4YaOQJvMgJ7i2TTZ6dlAK9ONv8znfhxrmPrm1jp9tWLEKZPR_FdI8FicrC4fLDEz6Ma4Nw7UO-cKNEza3dbjUtimyo2me-8O9CwMu0UBhG0LR4J8rNEwLl7CmBs3hkN8j1hMl3q-cVDjInef6vLW2cMzbJwptpp1rkNGNLFen6273UQAC9qQPpE0MoMmT9Q-9N2-3-xBam4dbYk5gqt3l3BLsCIzHYUeLMoVO6oA3DbIeF3hnvPBikN8gVOMCSk0ZsOZ_-g8V9jSJiRPKi9jOy77_LfEPOQzhXlGYkPFImXoLSk2PefZvuoIWOTYyHyfUAiYkC2JW96q5ZOynYyL27KCdS-J2d4BTPIXvYiVBGXN2cuDmY9kJA18lqnPKLiTFjUGnV53ojWItCYua3uFrxI"}
     */

    private String status;
    private String error;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * name : user1
         * email : user1@test.com
         * role : User
         * avatar : {"encoded":"data:image/png;base64,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","mime":"image/png","dirname":null,"basename":null,"extension":null,"filename":null}
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImZjODU2MDcxNWUyMGRmZjBjNjVjZTIwZWMzZjZhYmI0ZGY3YjJjOTdkNzc0ZDhmNzFjZDExNjNkYWQ0NmI3ZTMzMzI3ZTJjNmQxYzc0YTQ1In0.eyJhdWQiOiIxIiwianRpIjoiZmM4NTYwNzE1ZTIwZGZmMGM2NWNlMjBlYzNmNmFiYjRkZjdiMmM5N2Q3NzRkOGY3MWNkMTE2M2RhZDQ2YjdlMzMzMjdlMmM2ZDFjNzRhNDUiLCJpYXQiOjE1NjI1MDc5NzMsIm5iZiI6MTU2MjUwNzk3MywiZXhwIjoxNTk0MTMwMzczLCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.LMefg-FNPlm7j_DSjm0IbCQYE8L6RTrAlj87v3vNml7jChmBkoWHGSl-fU9l2XSwT_1LtOWc6pJmFe5A6N26uyA4qXf5UtQ3ZrRoDCzAWVYgtdxzoE1YBvwHP0SVr_EEMUZ8mRVQLQfXaX83xEzWpdnL2KDM65GfWAEyEaPuqSe_qPXEaabCebKW0g_naFJAGnVD1tr0HAyOhryttt0p-5rY1afiLqxyQtDLnzuCakBESTm5AGG9eG1Xqo-bdn5zDXML5yuUVbrZItLkmYSI4YaOQJvMgJ7i2TTZ6dlAK9ONv8znfhxrmPrm1jp9tWLEKZPR_FdI8FicrC4fLDEz6Ma4Nw7UO-cKNEza3dbjUtimyo2me-8O9CwMu0UBhG0LR4J8rNEwLl7CmBs3hkN8j1hMl3q-cVDjInef6vLW2cMzbJwptpp1rkNGNLFen6273UQAC9qQPpE0MoMmT9Q-9N2-3-xBam4dbYk5gqt3l3BLsCIzHYUeLMoVO6oA3DbIeF3hnvPBikN8gVOMCSk0ZsOZ_-g8V9jSJiRPKi9jOy77_LfEPOQzhXlGYkPFImXoLSk2PefZvuoIWOTYyHyfUAiYkC2JW96q5ZOynYyL27KCdS-J2d4BTPIXvYiVBGXN2cuDmY9kJA18lqnPKLiTFjUGnV53ojWItCYua3uFrxI
         */

        private int id;
        private String name;
        private String email;
        private String role;
        private AvatarBean avatar;
        private String token;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public AvatarBean getAvatar() {
            return avatar;
        }

        public void setAvatar(AvatarBean avatar) {
            this.avatar = avatar;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class AvatarBean {
            /**
             * encoded : data:image/png;base64,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
             * mime : image/png
             * dirname : null
             * basename : null
             * extension : null
             * filename : null
             */

            private String encoded;
            private String mime;
            private Object dirname;
            private Object basename;
            private Object extension;
            private Object filename;

            public String getEncoded() {
                return encoded;
            }

            public void setEncoded(String encoded) {
                this.encoded = encoded;
            }

            public String getMime() {
                return mime;
            }

            public void setMime(String mime) {
                this.mime = mime;
            }

            public Object getDirname() {
                return dirname;
            }

            public void setDirname(Object dirname) {
                this.dirname = dirname;
            }

            public Object getBasename() {
                return basename;
            }

            public void setBasename(Object basename) {
                this.basename = basename;
            }

            public Object getExtension() {
                return extension;
            }

            public void setExtension(Object extension) {
                this.extension = extension;
            }

            public Object getFilename() {
                return filename;
            }

            public void setFilename(Object filename) {
                this.filename = filename;
            }
        }
    }
}
