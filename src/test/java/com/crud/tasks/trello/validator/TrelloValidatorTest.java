package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
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
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void shouldValidateCardWithTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "other description", "left", "2");

        //When & Then
        trelloValidator.validateCard(trelloCard);

    }

    @Test
    public void shouldValidateCardWithoutTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "left", "2");

        //When & Then
        trelloValidator.validateCard(trelloCard);

    }

    @Test
    public void validateTrelloBoardsWithTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));

        //When
        List<TrelloBoard> validatedList = trelloValidator.validateTrelloBoards(trelloBoards);

        //
        assertEquals(0, validatedList.size());

    }

    @Test
    public void validateTrelloBoardsWithoutTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "name", trelloLists));

        //When
        List<TrelloBoard> validatedList = trelloValidator.validateTrelloBoards(trelloBoards);

        //
        assertEquals(1, validatedList.size());

    }
}