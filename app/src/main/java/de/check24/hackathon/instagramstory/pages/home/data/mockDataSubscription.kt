package de.check24.hackathon.instagramstory.pages.home.data

import com.google.gson.GsonBuilder
import de.check24.hackathon.instagramstory.R
import de.check24.hackathon.instagramstory.pages.data.Chapter
import de.check24.hackathon.instagramstory.pages.data.Story
import de.check24.hackathon.instagramstory.pages.data.StoryResponse

fun mockDataStories(): List<Story> {
    return listOf(
        Story(
            title = "From Fendace to The Simpsons, the 11 most viral moments of SS22",
            preview = "https://assets.vogue.in/photos/615e83b4922ad7d7353421c4/2:3/w_800%2Cc_limit/GettyImages-1342324002.jpg",
            id = 1,
            chapters = listOf(
                Chapter(
                    1001,
                    "${R.drawable.image_1}",
                    5000,
                    0,
                    "",
                    null,
                    null,
                    "IMAGE"
                ),
            )
        ),
        Story(
            title = "13 new restaurants and menus to try in Delhi, Bengaluru, Mumbai and Kolkata this October",
            preview = "https://assets.vogue.in/photos/615c49d65c25eba05fcaf31e/1:1/w_960,c_limit/Bombay-Canteen-crab-kulcha.jpg",
            id = 2,
            chapters = listOf()
        ),
        Story(
            title = "10 eyeshadow quads that are perfect for your next weekend getaway",
            preview = "https://assets.vogue.in/photos/6167d3fb9c06b58e75a5d450/1:1/w_960,c_limit/eyeshadow-weekend.jpg",
            id = 3,
            chapters = listOf()
        ),
        Story(
            title = "How a small brand from Goa is breaking new ground in sustainable design",
            preview = "https://assets.vogue.in/photos/6156d56fc6cf0fa9ecef2ab4/master/w_960,c_limit/bandit%20pen.jpg",
            id = 4,
            chapters = listOf()
        ),
        Story(
            title = "From Fendace to The Simpsons, the 11 most viral moments of SS22",
            preview = "https://assets.vogue.in/photos/615e83b4922ad7d7353421c4/2:3/w_800%2Cc_limit/GettyImages-1342324002.jpg",
            id = 5,
            chapters = listOf()
        ),
        Story(
            title = "Are perfect for your next weekend getaway",
            preview = "https://assets.vogue.in/photos/615e83b4922ad7d7353421c4/2:3/w_800%2Cc_limit/GettyImages-1342324002.jpg",
            id = 6,
            chapters = listOf()
        ),
        Story(
            title = "The Simpsons, the 11 most viral moments of SS22",
            preview = "https://assets.vogue.in/photos/6156d6a8c6cf0fa9ecef2ada/master/w_960,c_limit/artbag.jpg",
            id = 7,
            chapters = listOf()
        ),
        Story(
            title = "Virgo Horoscope Today: October 16, 2021",
            preview = "https://media.vogue.in/wp-content/uploads/2019/12/Virgo-866x488.jpg",
            id = 8,
            chapters = listOf()
        ),
        Story(
            title = "Hibernian 0-3 Dundee United: Nicky Clark, Ryan Edwards and Kieran Freeman secure win",
            preview = "https://e0.365dm.com/21/10/1600x900/skysports-dundee-united-scotland_5548624.jpg?20211016174702",
            id = 9,
            chapters = listOf()
        )
    )
}

fun mockAPIResponse(): List<Story> {
    return GsonBuilder().create().fromJson(
        "{\n" +
                "  \"stories\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"preview\": \"https://7esl.com/wp-content/uploads/2017/12/white-310x310.png\",\n" +
                "      \"title\": \"Just 2 pics\",\n" +
                "      \"chapters\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"url\": \"https://wallpapers.com/images/featured-full/mobile-58g8gv3r23zg29kw.jpg\",\n" +
                "          \"length\": 5000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"SEEN\",\n" +
                "          \"type\": \"IMAGE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"url\": \"https://e0.pxfuel.com/wallpapers/975/533/desktop-wallpaper-paper-walls-neon-plants-iphone-phone-galaxy-cool-neon-phone.jpg\",\n" +
                "          \"length\": 10000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"SEEN\",\n" +
                "          \"type\": \"IMAGE\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"preview\": \"https://7esl.com/wp-content/uploads/2017/12/yellow-310x310.png\",\n" +
                "      \"title\": \"2 pics + 1 vid\",\n" +
                "      \"chapters\": [\n" +
                "        {\n" +
                "          \"id\": 10,\n" +
                "          \"url\": \"https://wallpapers.com/images/featured-full/mobile-58g8gv3r23zg29kw.jpg\",\n" +
                "          \"length\": 1000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"SEEN\",\n" +
                "          \"type\": \"IMAGE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 11,\n" +
                "          \"url\": \"https://e0.pxfuel.com/wallpapers/975/533/desktop-wallpaper-paper-walls-neon-plants-iphone-phone-galaxy-cool-neon-phone.jpg\",\n" +
                "          \"length\": 5000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"IMAGE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 12,\n" +
                "          \"url\": \"https://dev.whost.ml/mixkit-pink-and-blue-ink-1192-medium.mp4\",\n" +
                "          \"length\": 10000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"preview\": \"https://7esl.com/wp-content/uploads/2017/12/blue-310x310.png\",\n" +
                "      \"title\": \"2 pics + 3 vid\",\n" +
                "      \"chapters\": [\n" +
                "        {\n" +
                "          \"id\": 20,\n" +
                "          \"url\": \"https://wallpapers.com/images/featured-full/mobile-58g8gv3r23zg29kw.jpg\",\n" +
                "          \"length\": 1000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"IMAGE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 21,\n" +
                "          \"url\": \"https://e0.pxfuel.com/wallpapers/975/533/desktop-wallpaper-paper-walls-neon-plants-iphone-phone-galaxy-cool-neon-phone.jpg\",\n" +
                "          \"length\": 5000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"IMAGE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 22,\n" +
                "          \"url\": \"https://dev.whost.ml/mixkit-pink-and-blue-ink-1192-medium.mp4\",\n" +
                "          \"length\": 10000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 23,\n" +
                "          \"url\": \"https://dev.whost.ml/mixkit-focusing-a-strange-blue-crystallized-texture-34502-medium.mp4\",\n" +
                "          \"length\": 7000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 24,\n" +
                "          \"url\": \"https://dev.whost.ml/mixkit-many-small-lights-out-of-focus-at-night-34378-medium.mp4\",\n" +
                "          \"length\": 11000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"preview\": \"https://7esl.com/wp-content/uploads/2017/12/red-310x310.png\",\n" +
                "      \"title\": \"Long video\",\n" +
                "      \"chapters\": [\n" +
                "        {\n" +
                "          \"id\": 30,\n" +
                "          \"url\": \"https://wallpapers.com/images/featured-full/mobile-58g8gv3r23zg29kw.jpg\",\n" +
                "          \"length\": 1000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"IMAGE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 31,\n" +
                "          \"url\": \"https://e0.pxfuel.com/wallpapers/975/533/desktop-wallpaper-paper-walls-neon-plants-iphone-phone-galaxy-cool-neon-phone.jpg\",\n" +
                "          \"length\": 5000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"IMAGE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 32,\n" +
                "          \"url\": \"https://dev.whost.ml/mixkit-pink-and-blue-ink-1192-medium.mp4\",\n" +
                "          \"length\": 10000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 33,\n" +
                "          \"url\": \"https://dev.whost.ml/mixkit-focusing-a-strange-blue-crystallized-texture-34502-medium.mp4\",\n" +
                "          \"length\": 7000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 34,\n" +
                "          \"url\": \"https://dev.whost.ml/mixkit-many-small-lights-out-of-focus-at-night-34378-medium.mp4\",\n" +
                "          \"length\": 11000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 35,\n" +
                "          \"url\": \"https://dev.whost.ml/cup-133507.mp4\",\n" +
                "          \"length\": 11000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\",\n" +
                "          \"startAt\": 0,\n" +
                "          \"endAt\": 8000\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 36,\n" +
                "          \"url\": \"https://dev.whost.ml/cup-133507.mp4\",\n" +
                "          \"length\": 11000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\",\n" +
                "          \"startAt\": 8000,\n" +
                "          \"endAt\": 14000\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 37,\n" +
                "          \"url\": \"https://dev.whost.ml/cup-133507.mp4\",\n" +
                "          \"length\": 11000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\",\n" +
                "          \"startAt\": 14000,\n" +
                "          \"endAt\": 30000\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 38,\n" +
                "          \"url\": \"https://dev.whost.ml/cup-133507.mp4\",\n" +
                "          \"length\": 11000,\n" +
                "          \"posted\": 1683885376,\n" +
                "          \"banners\": [],\n" +
                "          \"status\": \"NEW\",\n" +
                "          \"type\": \"VIDEO\",\n" +
                "          \"startAt\": 30000,\n" +
                "          \"endAt\": 40000\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}",
        StoryResponse::class.java
    ).stories
}