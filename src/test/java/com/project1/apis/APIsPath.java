package com.project1.apis;

public class APIsPath {

    public static final String baseURI = "https://petstore.swagger.io/v2/";

    public static final String storeGetInventory = "/store/inventory";
    public static final String storePlaceOrder = "/store/order";
    public static final String storeFindOrder = "/store/order/{orderId}";
    public static final String storeDeleteOrder = "/store/order/{orderId}";

    public static final String petUploadImage = "/pet/{petId}/uploadImage";
    public static final String addPet = "/pet";
    public static final String updatePet = "/pet";
    public static final String getPetStatus = "/pet/findByStatus";
    public static final String getPet = "/pet/{petId}";
    public static final String updatePetWithData = "/pet/{petId}";
    public static final String deletePrt = "/pet/{petId}";

    public static final String createUsersWithList = "/user/createWithList";
    public static final String getUser = "/user/{username}";
    public static final String updateUser = "/user/{username}";
    public static final String deleteUser = "/user/{username}";
    public static final String loginUser = "/user/login";
    public static final String logoutUser = "/user/logout";
    public static final String createUserWithArray = "/user/createWithArray";
    public static final String createUser = "/user";

}
