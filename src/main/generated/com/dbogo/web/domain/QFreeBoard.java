package com.dbogo.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoard is a Querydsl query type for FreeBoard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFreeBoard extends EntityPathBase<FreeBoard> {

    private static final long serialVersionUID = -1680864916L;

    public static final QFreeBoard freeBoard = new QFreeBoard("freeBoard");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> regDate = createDateTime("regDate", java.sql.Timestamp.class);

    public final StringPath regId = createString("regId");

    public final ListPath<Comment, QComment> replies = this.<Comment, QComment>createList("replies", Comment.class, QComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public QFreeBoard(String variable) {
        super(FreeBoard.class, forVariable(variable));
    }

    public QFreeBoard(Path<? extends FreeBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoard(PathMetadata metadata) {
        super(FreeBoard.class, metadata);
    }

}

