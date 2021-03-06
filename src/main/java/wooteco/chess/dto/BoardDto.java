package wooteco.chess.dto;

import wooteco.chess.domain.board.Board;
import wooteco.chess.domain.board.Position;
import wooteco.chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardDto {
    private Map<String, String> board;

    public BoardDto(Board board) {
        Map<Position, Piece> originalPieces = board.getPieces();
        Map<String, String> result = new HashMap<>();

        for (Position position : originalPieces.keySet()) {
            String pieceKey = originalPieces.get(position).getKey();
            result.put(position.getKey(), pieceKey);
        }
        this.board = result;
    }

    public void setBoard(Map<String, String> board) {
        this.board = board;
    }

    public Map<String, String> getBoard() {
        return this.board;
    }
}
