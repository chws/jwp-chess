package wooteco.chess.validator;

import wooteco.chess.domain.board.Board;
import wooteco.chess.domain.board.Position;
import wooteco.chess.exception.BlockedMovePathException;
import wooteco.chess.exception.InvalidDestinationException;
import wooteco.chess.exception.InvalidTurnException;
import wooteco.chess.exception.TeamKillException;

import java.util.List;

public abstract class MoveValidator {
    public void validate(Board board, Position source, Position target) {
        validateTurn(board, source);
        validateDestination(board, source, target);
        validatePath(board, source, target);
        validateTeamKill(board, source, target);
    }

    private void validateTurn(Board board, Position source) {
        if (board.isNotTurnOf(source)) {
            throw new InvalidTurnException(board.getTurn());
        }
    }

    private void validateDestination(Board board, Position source, Position target) {
        if (isNotPermittedMovement(board, source, target)) {
            throw new InvalidDestinationException();
        }
    }

    private void validatePath(Board board, Position source, Position target) {
        List<Position> movePath = movePathExceptSourceAndTarget(source, target);
        if (board.isExistAnyPieceAt(movePath)) {
            throw new BlockedMovePathException();
        }
    }

    private void validateTeamKill(Board board, Position source, Position target) {
        if (board.isExistAt(target) && board.isSameTeamBetween(source, target)) {
            throw new TeamKillException();
        }
    }

    protected abstract boolean isNotPermittedMovement(Board board, Position source, Position target);

    protected abstract List<Position> movePathExceptSourceAndTarget(Position source, Position target);
}
