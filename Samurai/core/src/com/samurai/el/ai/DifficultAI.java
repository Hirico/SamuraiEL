package com.samurai.el.ai;

import com.samurai.el.field.Field0;
import com.samurai.el.field.Field1;
import com.samurai.el.field.Field2;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.BlueAxe;
import com.samurai.el.player.BlueSpear;
import com.samurai.el.player.BlueSword;
import com.samurai.el.player.RedAxe;
import com.samurai.el.player.RedSpear;
import com.samurai.el.player.RedSword;
import com.samurai.el.player.Player;

public class DifficultAI extends AIProgram{
     public DifficultAI(){
    	 this.timeCell=0.5f;
    	 this.fieldx=GameInstance.getInstance().field.getSize().x;
    	 this.fieldy=GameInstance.getInstance().field.getSize().y;
     }
     public static void update(){
    	 this.field=GameInstance.getInstance().field.blocks;
     }
     public void reaction(){
       while(GameInstance.getInstance().currentTime%this.timeCell==0&GameInstance.getInstance().currentTime<GameInstance.getInstance().totalTime){   	 
    	 for(Player p:AIplayers){
    	    if(p instanceof BlueAxe){
    		   this.blueAxe();
    	    }
    	    if(p instanceof BlueSpear){
    	    	this.blueSpear();
    	    }
    	    if(p instanceof BlueSword){
    	    	this.blueSword();
    	    }
    	    if(p instanceof RedAxe){
    	    	this.redAxe();
    	    }
    	    if(p instanceof RedSpear){
    	    	this.redSpear();
    	    }
    	    if(p instanceof RedSword){
    	    	this.redSword();
    	    }
    	 }
       }
     }
     public void blueAxe(){
    	 int x=(int)GameInstance.getInstance().blueAxe.getPosition().x;
    	 int y=(int)GameInstance.getInstance().blueAxe.getPosition().y;
    	 if(GameInstance.getInstance().field instanceof Field0){
			 if(x+1<=this.fieldx&this.field[x+1][y].side==1){
				 GameInstance.getInstance().blueSpear.move(3);
			 }
			 else if(x+1<=this.fieldx&this.field[x+1][y].side!=1){
				 if(GameInstance.getInstance().blueSpear.occupiable()){
					 GameInstance.getInstance().blueSpear.turn(3);
					 GameInstance.getInstance().blueSpear.occupy();
				 }
			 }
			 else if(x+1>=this.fieldx&y-1>0&this.field[x-1][y].side==1){
    			 if(this.field[x][y-1].side==0){
    				 GameInstance.getInstance().blueAxe.move(1);
    				 
    			 }
    			 else{
    				 if(GameInstance.getInstance().blueAxe.occupiable()){
    					 GameInstance.getInstance().blueAxe.turn(1);
    					 GameInstance.getInstance().blueAxe.occupy();
    				 }
    			 }
    		 }
    		 else if(x-1>=0&this.field[x-1][y].side!=0){
    			 if(GameInstance.getInstance().blueAxe.occupiable()){
    				 GameInstance.getInstance().blueAxe.turn(3);
    				 GameInstance.getInstance().blueAxe.occupy();
    			 }
    		 }
    		 else if(x-1>0&x-2>=0&this.field[x-1][y].side==0&this.field[x-2][y].side!=0){
    			 GameInstance.getInstance().blueAxe.move(2);
    		 }
		 }
    	 if(GameInstance.getInstance().field instanceof Field1){
			 if(y-1>=0&this.field[x][y-1].side==1){
				 
				 GameInstance.getInstance().blueSpear.move(1);
			 }
			 else{
				 if(GameInstance.getInstance().blueSpear.occupiable()){
					 GameInstance.getInstance().blueSpear.turn(1);
					 GameInstance.getInstance().blueSpear.occupy();
				 }
			 }
		 }
     }
     public void blueSpear(){
    	 int i;
    	 int x=(int)GameInstance.getInstance().blueSpear.getPosition().x;
    	 int y=(int)GameInstance.getInstance().blueSpear.getPosition().y;
    	 for(i=1;i<5;i++){
    		 if(this.field[x-i][y].isVisible){    			 
    			 if(GameInstance.getInstance().blueSpear.occupiable()){
    				 GameInstance.getInstance().blueSpear.turn(2);
    				 GameInstance.getInstance().blueSpear.occupy();      				
    				 break;
    			 }
    		 }
    		 if(field[x+i][y].isVisible){
    			 if(GameInstance.getInstance().blueSpear.occupiable()){
    				 GameInstance.getInstance().blueSpear.turn(3);
    				 GameInstance.getInstance().blueSpear.occupy();    			
    				 break;
    			 }
    		 }
    		 if(field[x][y-i].isVisible){
    			 if(GameInstance.getInstance().blueSpear.occupiable()){
    				 GameInstance.getInstance().blueSpear.turn(1);
    				 GameInstance.getInstance().blueSpear.occupy();
    				 break;
    			 }
    		 }
    		 if(field[x][y+i].isVisible){
    			 if(GameInstance.getInstance().blueSpear.occupiable()){
    				 GameInstance.getInstance().blueSpear.turn(0);
    				 GameInstance.getInstance().blueSpear.occupy();
    				 break;
    			 }
    		 }   		 
    	 }
    	 if(i==5){
    		 if(GameInstance.getInstance().field instanceof Field0){
    			 if(y+1<=this.fieldy&this.field[x][y+1].side!=1){
    				 if(GameInstance.getInstance().blueSpear.occupiable()){
    				    GameInstance.getInstance().blueSpear.turn(0);
    				    GameInstance.getInstance().blueSpear.occupy();
    				 }
    			 }
    			 else if(y-1>=0&this.field[x][y-1].side!=1){
    				 if(GameInstance.getInstance().blueSpear.occupiable()){
    					 GameInstance.getInstance().blueSpear.turn(1);
    					 GameInstance.getInstance().blueSpear.occupy();
    				 }
    			 }
    			 else if(x+1<=this.fieldx&this.field[x+1][y].side==1){
    				 GameInstance.getInstance().blueSpear.move(3);
    			 }
    			 else{
    				  if(GameInstance.getInstance().blueSpear.occupiable()){
    					 GameInstance.getInstance().blueSpear.turn(3);
    					 GameInstance.getInstance().blueSpear.occupy();
    				  }
    			 }
    		 }
    		 if(GameInstance.getInstance().field instanceof Field1){
    			 if(x-1>=0&this.field[x-1][y].side!=1){
    				 if(GameInstance.getInstance().blueSpear.occupiable()){
    					 GameInstance.getInstance().blueSpear.turn(2);
    					 GameInstance.getInstance().blueSpear.occupy();;
    				 }
    			 }
    			 else if(x+1<=fieldx&this.field[x+1][y].side!=1){
    				 if(GameInstance.getInstance().blueSpear.occupiable()){
    					 GameInstance.getInstance().blueSpear.turn(3);
    					 GameInstance.getInstance().blueSpear.occupy();
    				 }
    			 }
    			 else if(y-1>=0&this.field[x][y-1].side==1){
    				 GameInstance.getInstance().blueSpear.move(1);
    			 }
    			 else{
    				 if(GameInstance.getInstance().blueSpear.occupiable()){
    					 GameInstance.getInstance().blueSpear.turn(1);
    					 GameInstance.getInstance().blueSpear.occupy();
    				 }
    			 }
    		 } 
    	 }
     }
     public void blueSword(){
    	 int x=(int)GameInstance.getInstance().blueSword.getPosition().x;
    	 int y=(int)GameInstance.getInstance().blueSword.getPosition().y;
     }
     public void redAxe(){
    	 int x=(int)GameInstance.getInstance().redAxe.getPosition().x;
    	 int y=(int)GameInstance.getInstance().redAxe.getPosition().y;
    	 if(GameInstance.getInstance().field instanceof Field0){
    		 if(x-1>=0&this.field[x+1][y].side==0){
    			 GameInstance.getInstance().redAxe.move(2);
    		 }
    		 else if(x-1>=0&this.field[x-1][y].side!=0){
    			 if(GameInstance.getInstance().redAxe.occupiable()){
    				 GameInstance.getInstance().redAxe.turn(2);
    				 GameInstance.getInstance().redAxe.occupy();
    			 }
    		 }
    		 else if(x-1<=0&y-1>0&this.field[x+1][y].side==0){
    			 if(this.field[x][y-1].side==0){
    				 GameInstance.getInstance().redAxe.move(1);
    				 
    			 }
    		 }
    		 else if(x+1<=fieldx&this.field[x+1][y].side!=0){
    			 if(GameInstance.getInstance().redAxe.occupiable()){
    				 GameInstance.getInstance().redAxe.turn(3);
    				 GameInstance.getInstance().redAxe.occupy();
    			 }
    		 }
    		 else if(x+1<=this.fieldx&x+2<this.fieldx&this.field[x+1][y].side==0&this.field[x+2][y].side!=0){
    			 GameInstance.getInstance().redAxe.move(3);
    		 }
    	}
     }
     public void redSpear(){
    	 int i;
    	 int x=(int)GameInstance.getInstance().redSpear.getPosition().x;
    	 int y=(int)GameInstance.getInstance().redSpear.getPosition().y;
    	 for(i=1;i<5;i++){
    		 if(field[x-i][y].isVisible){    			 
    			 if(GameInstance.getInstance().redSpear.occupiable()){
    				 GameInstance.getInstance().redSpear.turn(2);
    				 GameInstance.getInstance().redSpear.occupy();
    			 }
    		 }
    		 if(field[x+i][y].isVisible){
    			 if(GameInstance.getInstance().redSpear.occupiable()){
    				 GameInstance.getInstance().redSpear.turn(3);
    				 GameInstance.getInstance().redSpear.occupy();
    			 }
    		 }
    		 if(field[x][y-i].isVisible){
    			 if(GameInstance.getInstance().redSpear.occupiable()){
    				 GameInstance.getInstance().redSpear.turn(1);
    				 GameInstance.getInstance().redSpear.occupy();
    			 }
    		 }
    		 if(field[x][y+i].isVisible){
    			 if(GameInstance.getInstance().redSpear.occupiable()){
    				 GameInstance.getInstance().redSpear.turn(0);
    				 GameInstance.getInstance().redSpear.occupy();
    			 }
    		 }
    	 }
    	 if(i==5){
    		 if(GameInstance.getInstance().field instanceof Field0){
    			 if(y+1<=fieldy&this.field[x][y+1].side!=0){
    				 if(GameInstance.getInstance().redSpear.occupiable()){
    					 GameInstance.getInstance().redSpear.turn(0);
    					 GameInstance.getInstance().redSpear.occupy();
    				 }
    			 }
    			 else if(y-1>=0&this.field[x][y-1].side!=0){
    				 if(GameInstance.getInstance().redSpear.occupiable()){
    					 GameInstance.getInstance().redSpear.turn(1);
    					 GameInstance.getInstance().redSpear.occupy();
    				 }
    			 }
    			 else if(x-1>=0&this.field[x-1][y].side==0){
    				 GameInstance.getInstance().redSpear.move(2);
    			 }
    			 else if(x-1>=0&this.field[x-1][y].side!=0){
    				 if(GameInstance.getInstance().redSpear.occupiable()){
    					 GameInstance.getInstance().redSpear.turn(2);
    					 GameInstance.getInstance().redSpear.occupy();
    				 }
    			 }
    		 }
    		 if(GameInstance.getInstance().field instanceof Field1){
    			 if(x+1<=this.fieldx&this.field[x+1][y].side!=0){
    				 if(GameInstance.getInstance().redSpear.occupiable()){
    				 GameInstance.getInstance().redSpear.turn(3);
    				 GameInstance.getInstance().redSpear.occupy();
    				 }
    			 }
    			 else if(x-1<=0&this.field[x-1][y].side!=0){
    				 if(GameInstance.getInstance().redSpear.occupiable()){
    					 GameInstance.getInstance().redSpear.turn(2);
    					 GameInstance.getInstance().redSpear.occupy();
    				 }
    			 }
    			 else if(y+1<=this.fieldy&this.field[x][y+1].side==0){
    				 GameInstance.getInstance().redSpear.move(0);    				 
    			 }    				 
    			 else if(y+1<=this.fieldy&this.field[x][y+1].side!=0){
    				 if(GameInstance.getInstance().redSpear.occupiable()){
    					 GameInstance.getInstance().redSpear.turn(0);
    					 GameInstance.getInstance().redSpear.occupy();
    				 }
    			 }
    		 }
    	 }
    	 
     }
     public void redSword(){
    	 
     }
}
