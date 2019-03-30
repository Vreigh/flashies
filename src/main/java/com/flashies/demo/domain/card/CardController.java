package com.flashies.demo.domain.card;

import com.flashies.demo.domain.user.model.User;
import com.flashies.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.flashies.demo.domain.card.CardController.CARD_CONTEXT;

@RestController
@RequestMapping(path = CARD_CONTEXT)
@RequiredArgsConstructor
public class CardController {

   private final UserService userService;

   public static final String CARD_CONTEXT = "/card";

   @ResponseBody
   @GetMapping(path = "/example")
   public List<User> example() {
      return userService.getUsers();
   }
}
