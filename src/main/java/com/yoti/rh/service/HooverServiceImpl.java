package com.yoti.rh.service;

import com.yoti.rh.domain.HooveringEvent;
import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;
import com.yoti.rh.repository.HooverRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.yoti.rh.util.CoordinateUtils.isInBounds;

@Service
public class HooverServiceImpl implements HooverService {

    private final HooverRepository hooverRepository;

    public HooverServiceImpl(HooverRepository hooverRepository) {
        this.hooverRepository = hooverRepository;
    }

    @Override
    public HooverOutputDto processInstruction(HooverInputDto input) {
        HooverOutputDto output = hoover(input);
        HooveringEvent hooveringEvent = new HooveringEvent().setInput(input).setOutput(output);
        hooveringEvent = hooverRepository.createHooveringEvent(hooveringEvent);
        output.setId(hooveringEvent.getId());
        return output;
    }

    private HooverOutputDto hoover(HooverInputDto input) {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = input.getRoomSize();

        // Put the dirty patches into an index structure so we can easily find out whether we hoovered them
        Map<Pair<Long, Long>, Long[]> dirtyPatches = indexDirtyPatches(input);
        int dirtyPatchesHoovered = 0;

        Long[] position = input.getStartPosition();
        dirtyPatchesHoovered = incrementHooveredCountIfHoovered(position, dirtyPatches, dirtyPatchesHoovered);

        String directions = input.getDirections();
        if (directions != null) {
            for (char direction : input.getDirections().toCharArray()) {
                switch (Direction.valueOf(Character.toString(direction))) {
                    case N:
                        position = moveNorthIfInBounds(lower, upper, position);
                        break;
                    case S:
                        position = moveSouthIfInBounds(lower, upper, position);
                        break;
                    case E:
                        position = moveEastIfInBounds(lower, upper, position);
                        break;
                    case W:
                        position = moveWestIfInBounds(lower, upper, position);
                        break;
                }

                dirtyPatchesHoovered = incrementHooveredCountIfHoovered(position, dirtyPatches, dirtyPatchesHoovered);
            }
        }

        return new HooverOutputDto()
                .setEndPosition(position)
                .setDirtyPatchesHoovered(Integer.toUnsignedLong(dirtyPatchesHoovered));
    }

    private Map<Pair<Long, Long>, Long[]> indexDirtyPatches(HooverInputDto input) {
        return Stream.of(input.getDirtyPatches())
                .collect(Collectors.toMap(
                        dirtyPatch -> Pair.of(dirtyPatch[0], dirtyPatch[1]),
                        dirtyPatch -> dirtyPatch));
    }

    private int incrementHooveredCountIfHoovered(Long[] position, Map<Pair<Long, Long>, Long[]> dirtyPatches,
                                                 int dirtyPatchesHoovered) {
        Pair<Long, Long> positionKey = Pair.of(position[0], position[1]);
        return dirtyPatches.remove(positionKey) == null ? dirtyPatchesHoovered : dirtyPatchesHoovered + 1;
    }

    private Long[] moveNorthIfInBounds(Long[] lower, Long[] upper, Long[] current) {
        Long[] next = new Long[]{current[0], current[1] + 1};
        return isInBounds(lower, upper, next) ? next : current;
    }

    private Long[] moveSouthIfInBounds(Long[] lower, Long[] upper, Long[] current) {
        Long[] next = new Long[]{current[0], current[1] - 1};
        return isInBounds(lower, upper, next) ? next : current;
    }

    private Long[] moveEastIfInBounds(Long[] lower, Long[] upper, Long[] current) {
        Long[] next = new Long[]{current[0] + 1, current[1]};
        return isInBounds(lower, upper, next) ? next : current;
    }

    private Long[] moveWestIfInBounds(Long[] lower, Long[] upper, Long[] current) {
        Long[] next = new Long[]{current[0] - 1, current[1]};
        return isInBounds(lower, upper, next) ? next : current;
    }

    private enum Direction {

        N, S, E, W

    }

}
