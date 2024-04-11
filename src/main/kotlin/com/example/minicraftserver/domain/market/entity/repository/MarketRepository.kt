package com.example.minicraftserver.domain.market.entity.repository

import com.example.minicraftserver.domain.market.entity.Market
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MarketRepository : CrudRepository<Market, Long>