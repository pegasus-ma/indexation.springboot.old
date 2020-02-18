package com.pegasus.indexation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pegasus.indexation.entiry.Url;

public interface IDAOUrl extends PagingAndSortingRepository<Url, String> {

}
