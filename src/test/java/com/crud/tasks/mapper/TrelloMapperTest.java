package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        List<TrelloListDto> trelloListDtos1 = new ArrayList<>();
        List<TrelloListDto> trelloListDtos2 = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "another list", true);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "third name", false);
        trelloListDtos1.add(trelloListDto1);
        trelloListDtos1.add(trelloListDto2);
        trelloListDtos2.add(trelloListDto3);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board", trelloListDtos1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "second board", trelloListDtos2);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto1);
        trelloBoardDtoList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        Assert.assertEquals(trelloBoardDtoList.size(), trelloBoardList.size());
        Assert.assertEquals(trelloBoardDtoList.get(0).getName(), trelloBoardList.get(0).getName());
        Assert.assertEquals(trelloBoardDtoList.get(1).getLists().size(), trelloBoardList.get(1).getLists().size());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists1 = new ArrayList<>();
        List<TrelloList> trelloLists2 = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "list", false);
        TrelloList trelloList2 = new TrelloList("2", "another list", true);
        TrelloList trelloList3 = new TrelloList("3", "third name", false);
        trelloLists1.add(trelloList1);
        trelloLists2.add(trelloList2);
        trelloLists2.add(trelloList3);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "second board", trelloLists2);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        Assert.assertEquals(trelloBoardList.size(), trelloBoardDtoList.size());
        Assert.assertEquals(trelloBoardList.get(0).getName(), trelloBoardDtoList.get(0).getName());
        Assert.assertEquals(trelloBoardList.get(1).getLists().size(), trelloBoardDtoList.get(1).getLists().size());
    }

    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "list", false);
        TrelloListDto trelloListDto1 = new TrelloListDto("2", "another list", true);
        trelloListDtos.add(trelloListDto);
        trelloListDtos.add(trelloListDto1);

        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDtos);

        //Then
        Assert.assertEquals(trelloListDtos.size(), trelloList.size());
        Assert.assertEquals(trelloListDtos.get(0).getName(), trelloList.get(0).getName());
        Assert.assertEquals(trelloListDtos.get(1).isClosed(), trelloList.get(1).isClosed());

    }

    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "list", false);
        TrelloList trelloList2 = new TrelloList("2", "another list", true);
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assert.assertEquals(trelloLists.size(), trelloListDtos.size());
        Assert.assertEquals(trelloLists.get(0).getName(), trelloListDtos.get(0).getName());
        Assert.assertEquals(trelloLists.get(1).isClosed(), trelloListDtos.get(1).isClosed());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCard.getName(), trelloCardDto.getName());
        Assert.assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        Assert.assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        Assert.assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCardDto.getName(), trelloCard.getName());
        Assert.assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        Assert.assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }
}