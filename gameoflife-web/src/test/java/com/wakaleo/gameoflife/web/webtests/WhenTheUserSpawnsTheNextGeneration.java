package com.wakaleo.gameoflife.web.webtests;

import com.wakaleo.gameoflife.web.requirements.GameOfLifeApplication;
import com.wakaleo.gameoflife.web.webtests.pages.EnterGridPage;
import com.wakaleo.gameoflife.web.webtests.steps.PlayerSteps;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(ThucydidesRunner.class)
@Story(GameOfLifeApplication.RunSimulations.RunASimulation.class)
public class WhenTheUserSpawnsTheNextGeneration {
     
    @Managed(uniqueSession = true)
    WebDriver driver;

    @ManagedPages(defaultUrl = "http://localhost:9090")
    public Pages pages;

    @Steps
    PlayerSteps player;


    final static String[][] EMPTY_GRID
        = new String[][] {{".", ".", "."},
                          {".", ".", "."},
                          {".", ".", "."}};


   @Test
   public void anEmptyGridShouldProduceAnEmptyGrid() {
       String[][] expectedGrid  = new String[][]  {{".", ".", "."},
                                                   {".", ".", "."},
                                                   {".", ".", "."}};

       player.opens_home_page();
       player.chooses_to_start_a_new_game();
       player.starts_simulation();
       player.continues_simulation();
       player.should_see_grid(expectedGrid);

   }

   @Test
   public void aGridWithOneCellShouldProduceAnEmptyGrid() {
       String[][] expectedGrid  = new String[][]  {{".", ".", "."},
                                                   {".", ".", "."},
                                                   {".", ".", "."}};

       player.opens_home_page();
       player.chooses_to_start_a_new_game();
       player.clicks_on_cell_at(1,1);
       player.starts_simulation();
       player.continues_simulation();
       player.should_see_grid(expectedGrid);
   }

   @Test
   public void aGridWithTwoCellsShouldProduceAnEmptyGrid() {
       String[][] expectedGrid  = new String[][]  {{".", ".", "."},
                                                   {".", ".", "."},
                                                   {".", ".", "."}};

       player.opens_home_page();
       player.chooses_to_start_a_new_game();
       player.clicks_on_cell_at(1,1);
       player.clicks_on_cell_at(0,1);
       player.starts_simulation();
       player.continues_simulation();
       player.should_see_grid(expectedGrid);
   }


   @Test
    public void aStableCellSetShouldProduceTheSameSetOfCells() {
        String[][] expectedGrid  = new String[][]  {{"*", "*", "."},
                                                    {"*", "*", "."},
                                                    {".", ".", "."}};

       player.opens_home_page();
       player.chooses_to_start_a_new_game();
       player.clicks_on_cell_at(0,0);
       player.clicks_on_cell_at(0,1);
       player.clicks_on_cell_at(1,0);
       player.clicks_on_cell_at(1,1);
       player.starts_simulation();
       player.continues_simulation();
       player.should_see_grid(expectedGrid);
    }

    @Test
    public void aRotatingCellSetShouldProduceTheExpectedNewSetOfCells() {
        String[][] expectedGrid  = new String[][]  {{".", ".", "."},
                                                    {".", "*", "."},
                                                    {".", "*", "."}};

        player.opens_home_page();
        player.chooses_to_start_a_new_game();
        player.clicks_on_cell_at(0,0);
        player.clicks_on_cell_at(0,1);
        player.clicks_on_cell_at(1,0);
        player.clicks_on_cell_at(1,1);
        player.clicks_on_cell_at(1,2);
        player.starts_simulation();
        player.continues_simulation();
        player.continues_simulation();
        player.continues_simulation();
        player.should_see_grid(expectedGrid);
    }

    @Test
    public void aRotatingCellSetShouldProduceTheOriginalSetOfCellsAfterTwoGenerations() {
        String[][] expectedGrid  = new String[][]  {{".", ".", "."},
                                                    {"*", "*", "*"},
                                                    {".", ".", "."}};

        player.opens_home_page();
        player.chooses_to_start_a_new_game();
        player.clicks_on_cell_at(1,0);
        player.clicks_on_cell_at(1,1);
        player.clicks_on_cell_at(1,2);
        player.starts_simulation();
        player.continues_simulation();
        player.continues_simulation();
        player.should_see_grid(expectedGrid);
    }

    @Test
    public void aRotatingCellSetShouldProduceTheOriginalSetOfCellsAfterThreeGenerations() {
        String[][] expectedGrid  = new String[][]  {{".", "*", "."},
                                                    {".", "*", "."},
                                                    {".", "*", "."}};

        player.opens_home_page();
        player.chooses_to_start_a_new_game();
        player.clicks_on_cell_at(1,0);
        player.clicks_on_cell_at(1,1);
        player.clicks_on_cell_at(1,2);
        player.starts_simulation();
        player.continues_simulation();
        player.continues_simulation();
        player.continues_simulation();
        player.should_see_grid(expectedGrid);
    }

}