package com.alex.eko.paragon.utils.rest.doc;

public class ApiDoc {
    // Descriptions
    public static final String UPDATE_CREATE_DESCRIPTION = """
            `ID` - to update existing entity, without ID - create \n
            `DELETED` - to change visibility (deleted field). \n
            """;

    public static final String GET_ALL_DESCRIPTION = """
            Get all (deleted and not deleted) \n
            """;

    public static final String GET_ALL_PARAMETER_DESCRIPTION = """
            Filter by deleted productStatus.\s
            If `true` - returns only deleted.\s
            If `false` - returns only active.\s
            If `none` - returns not deleted (default value: false).""";

    public static final String GET_BY_ID_DESCRIPTION = """
            It returns entity by id even in case if entity is deleted \n
            """;

    // Summaries
    public static final String UPDATE_CREATE_SUMMARY = "Update/create entity";
    public static final String GET_ALL_SUMMARY = "Get all";
    public static final String GET_BY_ID_SUMMARY = "Get entity by id";



    public static final String MEDIA_TYPE = "application/json";
    public static final String PARAM_DELETED = "deleted";



    public static final String SUCCESS_CREATE_UPDATE_DESC = "Successfully created/updated entity.";
    public static final String ENTITY_NOT_FOUND_DESC = "ResourceNotFoundException. Ensure that provided entity ID exists in the system.";
    public static final String SUCCESS_RETRIEVED_DESC = "Successfully retrieved entity. It returns empty list if nothing found.";
    public static final String SUCCESS_RETRIEVED_BY_ID_DESC = "Successfully retrieved entity by id.";
    public static final String SUCCESS_RETRIEVED_CATEGORIES_BY_ID_DESC = "Successfully retrieved entity. \n" +
            "It returns the all categories from roots the most top category to all its child";




    public static final String GET_FEATURES_BY_CATEGORY_ID_SUMMARY = "Get all features by category id";
    public static final String GET_FEATURES_BY_CATEGORY_ID_DESCRIPTION = "Get all features by category id";
    public static final String CATEGORY_WITH_ROOTS_SUMMARY = "Get category by child category id (from child to root)";
    public static final String CATEGORY_WITH_ROOTS_DESCRIPTION = """
            It returns the category with the provided ID and all its parent categories up to the root category. \n
            The root category is the category with no parent category. \n
            The category with the provided ID is the child category. \n""";

    public static final String CATEGORY_WITH_CHILD_SUMMARY = "Get category by parent category id (from parent to all its child)";
    public static final String CATEGORY_WITH_CHILD_DESCRIPTION = """
            It returns the category with the provided ID and all its child categories. \n""";

    public static final String STATUS_DESCRIPTION = """
            Map of productStatus values and their active productStatus. Possible productStatus values include:
            - `HIDDEN`: Product is not shown in the online store.
            - `CUSTOMIZED`: Product can be customized, for example, adding a print.
            - `ON_SALE`: Product is available for sale.
            - `PRICE_SHOWN`: Price of the product is visible to clients.
            - `AVAILABLE_FOR_ORDER`: Product is available for order.
            - `CAN_BE_ORDERED_ONLY_ONLINE`: Product can be ordered only through the online shop.
            - `CAN_BE_ORDERED_ONLY_IN_STORE`: Product can be ordered only in the physical store.
            - `CAN_BE_ORDERED_WHEN_OUT_OF_STOCK`: Product can still be ordered even when out of stock.
            This field is write-only.
            """;


    public static final String PRODUCT_TYPE_DESCRIPTION = """
            Type of the product, represented by an enumeration. Possible values include:
            - `STANDARD`: A standard physical product.
            - `VIRTUAL`: A non-physical product, such as a digital download or service.
            - `PACKED`: A product that is packed together with others or sold as a bundle.
            This field is represented as a string.
            """;
}