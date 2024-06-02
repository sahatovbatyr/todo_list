package com.todo_list.todo_list_my_artifact.models;

public enum RoleType  {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    USER("USER"),
    GUEST("GUEST");

    private String displayName;

    RoleType( String displayName){
        this.displayName = displayName;
    }

    public String getDisplayeName() {
        return displayName;
    }

    public static RoleType getOne_byDisplayName(String displayName){

        for(RoleType role: RoleType.values() ) {
            if( role.getDisplayeName().equals(displayName)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Unknown display name on RoleType: "+displayName);
    }


}
