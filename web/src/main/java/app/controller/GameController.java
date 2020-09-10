package app.controller;

import app.common.GameService;
import app.utils.AttributeNames;
import app.utils.GameMappings;
import app.utils.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(GameMappings.HOME)
    public String home(){
        gameService.reset();
        return ViewNames.HOME;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart(){
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.PLAY)
    public String play(Model model){
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        if (gameService.isGameOver() || gameService.isGameWon()){
            return ViewNames.GAME_OVER;
        }else{
            return  ViewNames.PLAY;
        }
    }

    @PostMapping(GameMappings.PLAY)
    public String processGuess(@RequestParam int guess){
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;
    }
}
