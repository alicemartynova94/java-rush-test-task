package com.game.repository;

import com.game.entity.Player;
import com.game.entity.PlayerPage;
import com.game.entity.PlayerSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PlayerCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public PlayerCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Player> findAllWithFilters(PlayerPage playerPage,
                                           PlayerSearchCriteria playerSearchCriteria) {

        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery((Player.class));
        Root<Player> playerRoot = criteriaQuery.from(Player.class);
        Predicate predicate = getPredicate(playerSearchCriteria, playerRoot);
        criteriaQuery.where(predicate);
        setOrder(playerPage, criteriaQuery, playerRoot);
        TypedQuery<Player> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(playerPage.getPageNumber() * playerPage.getPageSize());
        typedQuery.setMaxResults(playerPage.getPageSize());

        Pageable pageable = getPagable(playerPage);
        long playersCount = getPlayersCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pageable, playersCount);

    }

    private long getPlayersCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Player> countRoot = countQuery.from(Player.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPagable(PlayerPage playerPage) {
        Sort sort = Sort.by(playerPage.getSortDirection(), playerPage.getSortBy());
        return PageRequest.of(playerPage.getPageNumber(), playerPage.getPageSize(), sort);
    }

    private void setOrder(PlayerPage playerPage,
                          CriteriaQuery<Player> criteriaQuery,
                          Root<Player> playerRoot) {
        if(playerPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(playerRoot.get(playerPage.getSortBy())));
        } else{
            criteriaQuery.orderBy(criteriaBuilder.desc(playerRoot.get(playerPage.getSortBy())));
        }
    }

    private Predicate getPredicate(PlayerSearchCriteria playerSearchCriteria, Root<Player> playerRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(playerSearchCriteria.getFirstName())) {
            predicates.add(
                    criteriaBuilder.like(playerRoot.get("firstName"), "%" + playerSearchCriteria.getFirstName() + "%")
            );
        }
        if (Objects.nonNull(playerSearchCriteria.getTitle())) {
            predicates.add(
                    criteriaBuilder.like(playerRoot.get("title"), "%" + playerSearchCriteria.getTitle() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
