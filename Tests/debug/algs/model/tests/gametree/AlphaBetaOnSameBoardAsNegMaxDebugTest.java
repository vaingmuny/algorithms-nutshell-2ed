package algs.model.tests.gametree;

import org.junit.Test;

import algs.model.gametree.IGameMove;
import algs.model.problems.tictactoe.debug.TicTacToeDebugger;
import algs.model.problems.tictactoe.model.BoardEvaluation;
import algs.model.problems.tictactoe.model.PlaceMark;
import algs.model.problems.tictactoe.model.Player;
import algs.model.problems.tictactoe.model.PlayerFactory;
import algs.model.problems.tictactoe.model.StraightLogic;
import algs.model.problems.tictactoe.model.TicTacToeBoard;
import algs.model.problems.tictactoe.model.TicTacToeState;

import junit.framework.TestCase;

public class AlphaBetaOnSameBoardAsNegMaxDebugTest extends TestCase {
	
	@Test
	public void testAlphaBetaDebug() {
		// create the TicTacToe game. Only instantiate the proper class
	    // that you want to play.
		StraightLogic logic = new StraightLogic();
		
		// 2-move lookahead, using the BoardEvaluation function as described in Nilsson.
	    Player xPlayer = PlayerFactory.createPlayerWithPly(PlayerFactory.MiniMax, Player.XMARK, 2);
	    xPlayer.logic(logic);
	    xPlayer.score(new BoardEvaluation());
	    
	    // 2-move lookahead, for O.
	    Player oPlayer = PlayerFactory.createPlayerWithPly(PlayerFactory.MiniMax, Player.OMARK, 2);
	    oPlayer.logic(logic);
	    oPlayer.score(new BoardEvaluation());
	    
	    TicTacToeBoard board = new TicTacToeBoard();
	    TicTacToeState state = new TicTacToeState(board, logic);
	    
	     // pre-initialize board to 1.5 moves
	    new PlaceMark(1,1, (Player) xPlayer).execute(state);
	    new PlaceMark(0,0, (Player) oPlayer).execute(state);
	    new PlaceMark(0,2, (Player) xPlayer).execute(state);

	    // two ply lookahead.
	    algs.model.gametree.debug.AlphaBetaEvaluation abe =
	    	new algs.model.gametree.debug.AlphaBetaEvaluation(2);
	    TicTacToeDebugger std = new TicTacToeDebugger();
	    abe.debug(std);
		IGameMove move = abe.bestMove (state, oPlayer, xPlayer);
		System.out.println ("best move:" + move);
		System.out.println (std.getInputString());
		
		// we really know that this move is a placemark
		assertEquals (2, ((PlaceMark)move).getColumn());
		assertEquals (0, ((PlaceMark)move).getRow());
		
		// for closure on toString methods
		abe.toString();
	}
	
	
}
