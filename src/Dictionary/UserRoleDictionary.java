package Dictionary;

/**
 * Enum for different roles
 */
public enum UserRoleDictionary {
    ROLE_USER, ROLE_ADMIN, ROLE_EMPLOYEE, ROLE_MANAGER;

    /**
     * Stringifies role
     * @return string value of the role
     */
    @Override
    public String toString() {
        switch (this) {
            case ROLE_USER:
                return "ROLE_USER";
            case ROLE_ADMIN:
                return "ROLE_ADMIN";
            case ROLE_EMPLOYEE:
                return "ROLE_EMPLOYEE";
            case ROLE_MANAGER:
                return "ROLE_MANAGER";
            default:
                return "UNDEFINED";
        }
    }
}
