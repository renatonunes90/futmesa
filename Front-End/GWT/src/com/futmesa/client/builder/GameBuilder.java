package com.futmesa.client.builder;

import com.futmesa.client.businessinteligence.Game;
import com.google.gwt.core.client.JavaScriptObject;

/**
 */
public class GameBuilder
{

   /**
    */
   private GameBuilder()
   {}
   
   @SuppressWarnings ( "unchecked" )
   public static Game buildGame(String name1, String name2, int score1, int score2)
   {
      Game g = ( Game ) JavaScriptObject.createObject();
      g.setPlayer1Name(name1);
      g.setPlayer2Name(name2);
      g.setScore1(score1);
      g.setScore2(score2);
      return g;
   }

}
