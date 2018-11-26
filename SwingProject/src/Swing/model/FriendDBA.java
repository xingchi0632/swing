package Swing.model;

import java.util.ArrayList;


//인터페이스 추상 
//기본 표준화 - > 이렇게 합시다라고 하면 개발자가 이렇게 개발하면 됨 

public interface FriendDBA {
	//추가
	//보기
	//검색
	
	public void friendInsert(Friend f);
	public ArrayList<Friend> friendView();
	public ArrayList<Friend> friendSearch(String str,String word);
	
	

}
