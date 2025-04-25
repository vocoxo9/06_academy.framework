package com.kh.apitest.exercise.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.apitest.exercise.model.vo.Exercise;

@Repository
public class ExerciseDAO {

	public int insertExercise(SqlSession sqlSession, Exercise exercise) {
        return sqlSession.insert("exerciseMapper.insertExercise", exercise);
    }
}
