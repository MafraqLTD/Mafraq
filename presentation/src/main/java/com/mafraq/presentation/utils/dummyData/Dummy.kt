package com.mafraq.presentation.utils.dummyData

import com.mafraq.presentation.features.home.components.AdModel

internal object Dummy {
 val dummyAds = listOf(
        AdModel(
            title = "Test1",
            description = "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
            imageUrl = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"
        ),
        AdModel(
            title = "Test2",
            description = "readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look",
            imageUrl = "https://cdn.pixabay.com/photo/2016/05/05/02/37/sunset-1373171_1280.jpg"
        ),
        AdModel(
            title = "اختبار",
            description = "وفي لم نهاية الربيع، وانتهاءً. و جدول الإكتفاء تحت. تم هذه بوابة مدينة شواطيء. عرض وصغار وإيطالي لم, عل به، يرتبط وايرلندا. خلاف أملاً عدم عل, بحث مع دخول بقعة وبعد.\n",
            imageUrl = "https://cdn.pixabay.com/photo/2016/09/29/14/24/landscape-1702945_960_720.jpg"
        ),
    )
}
