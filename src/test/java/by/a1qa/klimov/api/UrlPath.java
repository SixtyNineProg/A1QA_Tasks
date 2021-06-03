package by.a1qa.klimov.api;

public enum UrlPath {
    WALL_POST {
        @Override
        public String toString() {
            return "method/wall.post";
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
    },
    WALL_CRATE_COMMENT {
        @Override
        public String toString() {
            return "method/wall.createComment";
        }
    },
    LIKES_IS_LIKED {
        @Override
        public String toString() {
            return "method/likes.isLiked";
        }
    },
    WALL_DELETE {
        @Override
        public String toString() {
            return "method/wall.delete";
        }
    }
}
