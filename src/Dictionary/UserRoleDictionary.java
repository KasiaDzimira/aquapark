package Dictionary;

public enum  UserRoleDictionary {
    ROLE_USER, ROLE_ADMIN, ROLE_EMPLOYEE;

    @Override
    public String toString() {
        switch (this) {
            case ROLE_USER:
                return "ROLE_USER";
            case ROLE_ADMIN:
                return "ROLE_ADMIN";
            case ROLE_EMPLOYEE:
                return "ROLE_EMPLOYEE";
            default:
                return "UNDEFINED";
        }
    }
}
