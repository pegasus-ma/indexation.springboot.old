package com.pegasus.indexation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pegasus.indexation.entiry.Url;

@Repository
public interface IDAOUrl extends PagingAndSortingRepository<Url, String> {

}
