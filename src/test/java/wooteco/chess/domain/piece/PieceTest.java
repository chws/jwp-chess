package wooteco.chess.domain.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    void equalsTest() {
        Piece piece1 = new Piece(Team.BLACK, PieceType.PAWN);
        Piece piece2 = new Piece(Team.BLACK, PieceType.PAWN);
        assertThat(piece1 == piece2).isFalse();
    }
}
