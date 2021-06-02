package by.a1qa.klimov.api;

public enum UrlPath {
    WALL_POST {
        @Override
        public String toString() {
            return "method/wall.post";
        }
    },
    WALL_GET {
        @Override
        public String toString() {
            return "method/wall.get";
        }
    },
    USERS_GET {
        @Override
        public String toString() {
            return "method/users.get";
        }
    },
    WALL_EDIT_POST {
        @Override
        public String toString() {
            return "method/wall.edit";
        }
    },
    PHOTOS_GET_UPLOAD_SERVER {
        @Override
        public String toString() {
            return "method/photos.getUploadServer";
        }
    },
    PHOTOS_SAVE {
        @Override
        public String toString() {
            return "method/photos.save";
        }
    }
}
