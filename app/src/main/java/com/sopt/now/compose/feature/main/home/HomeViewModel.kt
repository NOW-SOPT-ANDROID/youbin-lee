package com.sopt.now.compose.feature.main.home

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.data.model.FriendInfo

class HomeViewModel : ViewModel() {

    val friendList = listOf<FriendInfo>(
        FriendInfo.MyProfile(
            name = "이유빈",
            profileImage = R.drawable.img_my_profile,
            profileImageEtc = R.drawable.img_my_profile_etc
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile1,
            name = "우상욱",
            selfDescription = "손흥민",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile3,
            name = "최준서",
            selfDescription = "묵찌빠를 전공했단 사아실",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile4,
            name = "김언지",
            selfDescription = "너 내 도도독..",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile5,
            name = "박동민",
            selfDescription = "밀양박씨 36대손인 나",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile6,
            name = "배찬우",
            selfDescription = "제..목을 입력해주세요",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile7,
            name = "강문수",
            selfDescription = "시험 화이탱탱~~",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile8,
            name = "이현진",
            selfDescription = "그래서 현진언니 파트장 언제 해요?",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile9,
            name = "이나경",
            selfDescription = "나갱~~~보고시퍼~~",
        ),
    )

}