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
    POSTS {
        @Override
        public String toString() {
            return "posts";
        }
    },
    POSTS_ID {
        @Override
        public String toString() {
            return "posts/";
        }
    },
    USERS {
        @Override
        public String toString() {
            return "users";
        }
    },
    USERS_ID {
        @Override
        public String toString() {
            return "users/";
        }
    },
}
