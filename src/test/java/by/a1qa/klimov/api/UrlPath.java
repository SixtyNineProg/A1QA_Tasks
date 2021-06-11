package by.a1qa.klimov.api;

public enum UrlPath {
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
