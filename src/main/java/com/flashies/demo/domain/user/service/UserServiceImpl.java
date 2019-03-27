package com.flashies.demo.domain.user.service;

import com.flashies.demo.domain.user.dao.UserDao;
import com.flashies.demo.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private final SqlSession sqlSession;

   @Override
   public List<User> getUsers() {
      UserDao userDao = sqlSession.getMapper(UserDao.class);
      return userDao.getUsers();
   }
}
