package wooteco.chess.validator;

import wooteco.chess.domain.board.Board;
import wooteco.chess.domain.board.Position;
import wooteco.chess.domain.board.Rank;
import wooteco.chess.domain.piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PawnMoveValidator extends MoveValidator {
    private static final int MINIMUM_MOVEMENT_RANK = 1;
    private static final int MAXIMUM_MOVEMENT_RANK = 2;

    private boolean isPermittedMovement(Board board, Position source, Position target) {
        return board.isExistEnemyFrontLeft(source, target)
                || board.isExistEnemyFrontRight(source, target)
                || isMoveOneOrTwoSquaresForward(board, source, target);
    }

    private boolean isMoveOneOrTwoSquaresForward(Board board, Position source, Position target) {
        if (source.isNotSameFile(target)) {
            return false;
        }
        if (board.isExistAt(target)) {
            return false;
        }

        Piece piece = board.getPiece(source);
        int forwardMoveAmountOfRank = board.forwardMoveAmountOfRank(source, target);
        if (piece.isWhite() && source.isSameRank(Rank.TWO)) {
            return forwardMoveAmountOfRank >= MINIMUM_MOVEMENT_RANK && forwardMoveAmountOfRank <= MAXIMUM_MOVEMENT_RANK;
        }
        if (piece.isBlack() && source.isSameRank(Rank.SEVEN)) {
            return forwardMoveAmountOfRank >= MINIMUM_MOVEMENT_RANK && forwardMoveAmountOfRank <= MAXIMUM_MOVEMENT_RANK;
        }
        return forwardMoveAmountOfRank == MINIMUM_MOVEMENT_RANK;
    }

    @Override
    protected boolean isNotPermittedMovement(Board board, Position source, Position target) {
        return !isPermittedMovement(board, source, target);
    }

    @Override
    protected List<Position> movePathExceptSourceAndTarget(Position source, Position target) {
        if (source.differenceOfRank(target) == MAXIMUM_MOVEMENT_RANK) {
            List<Rank> ranks = Rank.valuesBetween(source.getRank(), target.getRank());
            return ranks.stream()
                    .map(rank -> Position.of(source.getFile(), rank))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
