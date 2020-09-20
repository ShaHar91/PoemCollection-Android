package com.shahar91.poems.data

import io.realm.Realm

class InitialRealmData : Realm.Transaction {
    override fun execute(realm: Realm) {
//        realm.deleteAll()
//
//        realm.createOrUpdateAllFromJson(Poem::class.java, getPoemsAsString())
    }

    private fun getPoemsAsString(): String {
        return "[\n" +
                "  {\n" +
                "    \"poemId\": 0,\n" +
                "    \"title\": \"See Me As Your Like\",\n" +
                "    \"body\": \"only your kind would say Adam is white; you would boastfully say the devil is black; now i ask...\",\n" +
                "    \"writer\": {\n" +
                "      \"userId\": 0,\n" +
                "      \"email\": \"christiano.bolla@outlook.com\",\n" +
                "      \"fullName\": \"Christiano Bolla\",\n" +
                "      \"pictureUrl\": null\n" +
                "    },\n" +
                "    \"reviews\": [\n" +
                "      {\n" +
                "        \"reviewId\": 1,\n" +
                "        \"rating\": 2.0000000e+00,\n" +
                "        \"body\": \"Second body\",\n" +
                "        \"createdAt\": 1583435400,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 5,\n" +
                "          \"email\": \"zaid.mcdermott@outlook.com\",\n" +
                "          \"fullName\": \"Zaid Mcdermott\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 2,\n" +
                "        \"rating\": 5.0000000e-01,\n" +
                "        \"body\": \"Third body\",\n" +
                "        \"createdAt\": 1290211800,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 3,\n" +
                "          \"email\": \"tj.weber@outlook.com\",\n" +
                "          \"fullName\": \"Tj Weber\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 3,\n" +
                "        \"rating\": 5.0000000e+00,\n" +
                "        \"body\": \"Fourth body\",\n" +
                "        \"createdAt\": 1580635200,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 1,\n" +
                "          \"email\": \"els.schuurmans@outlook.com\",\n" +
                "          \"fullName\": \"Els Schuurmans\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"categoryId\": 0,\n" +
                "        \"name\": \"Beauty\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"categoryId\": 2,\n" +
                "        \"name\": \"Cyber\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"poemId\": 1,\n" +
                "    \"title\": \"Cyber Love\",\n" +
                "    \"body\": \"The night I met you in words only on a screen...\",\n" +
                "    \"writer\": {\n" +
                "      \"userId\": 1,\n" +
                "      \"email\": \"els.schuurmans@outlook.com\",\n" +
                "      \"fullName\": \"Els Schuurmans\",\n" +
                "      \"pictureUrl\": null\n" +
                "    },\n" +
                "    \"reviews\": [\n" +
                "      {\n" +
                "        \"reviewId\": 0,\n" +
                "        \"rating\": 1.0000000e+00,\n" +
                "        \"body\": \"First body\",\n" +
                "        \"createdAt\": 1580634600,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 0,\n" +
                "          \"email\": \"christiano.bolla@outlook.com\",\n" +
                "          \"fullName\": \"Christiano Bolla\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 1,\n" +
                "        \"rating\": 2.0000000e+00,\n" +
                "        \"body\": \"Second body\",\n" +
                "        \"createdAt\": 1583435400,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 5,\n" +
                "          \"email\": \"zaid.mcdermott@outlook.com\",\n" +
                "          \"fullName\": \"Zaid Mcdermott\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 2,\n" +
                "        \"rating\": 5.0000000e-01,\n" +
                "        \"body\": \"Third body\",\n" +
                "        \"createdAt\": 1290211800,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 3,\n" +
                "          \"email\": \"tj.weber@outlook.com\",\n" +
                "          \"fullName\": \"Tj Weber\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 3,\n" +
                "        \"rating\": 5.0000000e+00,\n" +
                "        \"body\": \"Fourth body\",\n" +
                "        \"createdAt\": 1580635200,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 1,\n" +
                "          \"email\": \"els.schuurmans@outlook.com\",\n" +
                "          \"fullName\": \"Els Schuurmans\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"categoryId\": 2,\n" +
                "        \"name\": \"Cyber\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"poemId\": 2,\n" +
                "    \"title\": \"Birth Of A Child\",\n" +
                "    \"body\": \"It's a new day, with a brand new start,...\",\n" +
                "    \"writer\": {\n" +
                "      \"userId\": 2,\n" +
                "      \"email\": \"myles.friedman@outlook.com\",\n" +
                "      \"fullName\": \"Myles Friedman\",\n" +
                "      \"pictureUrl\": null\n" +
                "    },\n" +
                "    \"reviews\": [\n" +
                "      {\n" +
                "        \"reviewId\": 1,\n" +
                "        \"rating\": 2.0000000e+00,\n" +
                "        \"body\": \"Second body\",\n" +
                "        \"createdAt\": 1583435400,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 5,\n" +
                "          \"email\": \"zaid.mcdermott@outlook.com\",\n" +
                "          \"fullName\": \"Zaid Mcdermott\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 2,\n" +
                "        \"rating\": 5.0000000e-01,\n" +
                "        \"body\": \"Third body\",\n" +
                "        \"createdAt\": 1290211800,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 3,\n" +
                "          \"email\": \"tj.weber@outlook.com\",\n" +
                "          \"fullName\": \"Tj Weber\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 3,\n" +
                "        \"rating\": 5.0000000e+00,\n" +
                "        \"body\": \"Fourth body\",\n" +
                "        \"createdAt\": 1580635200,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 1,\n" +
                "          \"email\": \"els.schuurmans@outlook.com\",\n" +
                "          \"fullName\": \"Els Schuurmans\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"categoryId\": 1,\n" +
                "        \"name\": \"Birth\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"poemId\": 3,\n" +
                "    \"title\": \"Losing My Mind\",\n" +
                "    \"body\": \"I'm sitting in the room...\",\n" +
                "    \"writer\": {\n" +
                "      \"userId\": 3,\n" +
                "      \"email\": \"tj.weber@outlook.com\",\n" +
                "      \"fullName\": \"Tj Weber\",\n" +
                "      \"pictureUrl\": null\n" +
                "    },\n" +
                "    \"reviews\": [\n" +
                "      {\n" +
                "        \"reviewId\": 0,\n" +
                "        \"rating\": 1.0000000e+00,\n" +
                "        \"body\": \"First body\",\n" +
                "        \"createdAt\": 1580634600,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 0,\n" +
                "          \"email\": \"christiano.bolla@outlook.com\",\n" +
                "          \"fullName\": \"Christiano Bolla\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 1,\n" +
                "        \"rating\": 2.0000000e+00,\n" +
                "        \"body\": \"Second body\",\n" +
                "        \"createdAt\": 1583435400,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 5,\n" +
                "          \"email\": \"zaid.mcdermott@outlook.com\",\n" +
                "          \"fullName\": \"Zaid Mcdermott\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 2,\n" +
                "        \"rating\": 5.0000000e-01,\n" +
                "        \"body\": \"Third body\",\n" +
                "        \"createdAt\": 1290211800,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 3,\n" +
                "          \"email\": \"tj.weber@outlook.com\",\n" +
                "          \"fullName\": \"Tj Weber\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"reviewId\": 3,\n" +
                "        \"rating\": 5.0000000e+00,\n" +
                "        \"body\": \"Fourth body\",\n" +
                "        \"createdAt\": 1580635200,\n" +
                "        \"user\": {\n" +
                "          \"userId\": 1,\n" +
                "          \"email\": \"els.schuurmans@outlook.com\",\n" +
                "          \"fullName\": \"Els Schuurmans\",\n" +
                "          \"pictureUrl\": null\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"categoryId\": 3,\n" +
                "        \"name\": \"Dark\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]"
    }
}