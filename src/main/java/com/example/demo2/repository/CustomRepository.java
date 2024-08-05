package com.example.demo2.repository;

import com.example.demo2.dto.RRR;
import com.example.demo2.entity.B;
import com.example.demo2.entity.Member;
import com.example.demo2.entity.TestE;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo2.entity.QB.b;
import static com.example.demo2.entity.QMember.member;
import static com.example.demo2.entity.QTTable.tTable;
import static com.example.demo2.entity.QTestE.testE;
import static com.querydsl.core.types.ExpressionUtils.as;

@Repository
@Log4j2
@RequiredArgsConstructor
public class CustomRepository {

    private final JPAQueryFactory queryFactory;

    public static OrderSpecifier<?>[] getOrderSpecifier(Sort sort) {
        return sort.stream()
                .map(order -> {
                    switch (order.getDirection()) {
                        case ASC:
                            return Expressions.stringPath(order.getProperty()).asc();
                        case DESC:
                            return Expressions.stringPath(order.getProperty()).desc();
                        default:
                            throw new IllegalArgumentException("Unsupported sort direction: " + order.getDirection());
                    }
                })
                .toArray(OrderSpecifier[]::new);
    }

    public record Response2(Long memberId, String email, List<TestEDTO> testEList) {
    }

    public record TestEDTO(Long testId, String ttt, tDTO tDTO) {
    }

    public record tDTO(Long tId, String ttt) {
    }

    public Page find(Pageable pageable) {
        List<Response2> results = queryFactory
                .selectFrom(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .transform(GroupBy.groupBy(member.memberId).list(
                        Projections.constructor(Response2.class
                                , member.memberId
                                , member.email
                                , Projections.list(Projections.constructor(TestEDTO.class,
                                        testE.testId,
                                        tTable.ttt,
                                        Projections.constructor(tDTO.class,
                                                tTable.tId,
                                                tTable.ttt
                                        )
                                ))
                        )
                ));

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member);

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    public Page find2(Pageable pageable) {
        List<Long> ids = queryFactory
                .selectDistinct(member.memberId)
                .from(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        List<Response2> results = queryFactory
                .selectFrom(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .where(member.memberId.in(ids))
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .transform(GroupBy.groupBy(member.memberId).list(
                        Projections.constructor(Response2.class
                                , member.memberId
                                , member.email
                                , Projections.list(Projections.constructor(TestEDTO.class,
                                        testE.testId,
                                        tTable.ttt,
                                        Projections.constructor(tDTO.class,
                                                tTable.tId,
                                                tTable.ttt
                                        )
                                ))
                        )
                ));

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member);

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    public Page find3(Pageable pageable) {
        List<Member> results = queryFactory.selectFrom(member)
                .leftJoin(member.test, testE).fetchJoin()
                .leftJoin(testE.tTable, tTable).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member);
        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    public Page find4(Pageable pageable) {
        List<Member> results = queryFactory.selectFrom(member)
                .leftJoin(member.test, testE).fetchJoin()
                .leftJoin(testE.tTable, tTable).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        log.debug(results);

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member);
        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    public Page find5(Pageable pageable) {
        List<Member> results = queryFactory.selectFrom(member)
                .leftJoin(member.test, testE).fetchJoin()
                .leftJoin(member.b, b).fetchJoin()
                .leftJoin(testE.tTable, tTable).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member);
        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    public record Response3(Long memberId, String email, List<TestE> eList, List<B> bList) {
    }

    public Page<Response3> find6(Pageable pageable) {
        List<Member> results = queryFactory.selectFrom(member)
                .leftJoin(member.test, testE).fetchJoin()
                .leftJoin(testE.tTable, tTable).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        queryFactory
                .selectFrom(member)
                .leftJoin(member.b, b).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        List<Response3> response = results
                .stream()
                .map(member -> new Response3(member.getMemberId(), member.getEmail(), member.getTest(), member.getB()))
                .toList();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member);
        return PageableExecutionUtils.getPage(response, pageable, countQuery::fetchOne);
    }

    public record rrr(Long memberId, List<TestE> tt) {}
    public record TTTT(Long testId, Long tId, TDDD tddd) {}

    public record TDDD(String ttt) {}

    public List find7(Pageable pageable) {
        List<Long> ids = queryFactory
                .selectDistinct(member.memberId)
                .from(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .where(
                        tTable.ttt.contains("1")
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        List<RRR> results = queryFactory
                .select(
                        Projections.fields(RRR.class,
                                member.memberId,
                                as(Projections.list(Projections.constructor(TTTT.class,
                                        testE.testId,
                                        testE.tId,
                                        Projections.constructor(TDDD.class,
                                                tTable.ttt
                                                )
                                )), "test")
                        )
                )
                .from(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .where(
                        member.memberId.in(ids)
                )
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

//        log.debug(ids);
        return results;
    }

    public Page<Response3> find8(Pageable pageable) {
        List<Long> ids = queryFactory
                .selectDistinct(member.memberId)
                .from(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .leftJoin(member.b, b)
                .where(
                        tTable.ttt.contains("1")
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        List<Member> results = queryFactory
                .selectFrom(member)
                .leftJoin(member.test, testE).fetchJoin()
                .leftJoin(testE.tTable, tTable).fetchJoin()
                .where(member.memberId.in(ids))
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        queryFactory
                .selectFrom(member)
                .leftJoin(member.b, b).fetchJoin()
                .where(member.memberId.in(ids))
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        List<Response3> response = results
                .stream()
                .map(member -> new Response3(member.getMemberId(), member.getEmail(), member.getTest(), member.getB()))
                .toList();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.memberId.countDistinct())
                .from(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .where(
                        tTable.ttt.contains("1")
                );
        return PageableExecutionUtils.getPage(response, pageable, countQuery::fetchOne);
    }

    public List find9(Pageable pageable) {
        List<Long> ids = queryFactory
                .selectDistinct(member.memberId)
                .from(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .where(
                        tTable.ttt.contains("1")
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

        List<RRR> results = queryFactory
                .select(
                        Projections.fields(RRR.class,
                                member.memberId,
                                as(Projections.list(testE), "test")
                        )
                )
                .from(member)
                .leftJoin(testE).on(member.memberId.eq(testE.memberId))
                .leftJoin(tTable).on(testE.testId.eq(tTable.tId))
                .where(
                        member.memberId.in(ids)
                )
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .fetch();

//        log.debug(ids);
        return results;
    }
}
