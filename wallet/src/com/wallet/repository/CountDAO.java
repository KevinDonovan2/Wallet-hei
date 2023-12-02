package com.wallet.repository;

import com.wallet.entities.Count;

import java.util.List;

public interface CountDAO {
    void insert(Count e);
    List<Count> findAll();
    Count findById(int id);
    void update (Count e);
    Count delete (int id);
}
