package com.baidu.highflip.core.entity.dag.common;

import com.baidu.highflip.core.utils.ProtoUtils;
import highflip.HighflipMeta;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class NodeInputRef {

    Integer index;

    String name;

    String description;

    String fromNode;

    String fromOutput;

    public static HighflipMeta.NodeInputProto toProto(com.baidu.highflip.core.entity.dag.common.NodeInputRef ref) {
        HighflipMeta.NodeInputProto.Builder builder = HighflipMeta.NodeInputProto
                .newBuilder()
                .setName(ref.getName())
                .setFromNode(ref.getFromNode())
                .setFromOutput(ref.getFromOutput());

        ProtoUtils.setOptional(builder, "Description", ProtoUtils.ofString(ref.getDescription()));
        return builder.build();
    }

    public static com.baidu.highflip.core.entity.dag.common.NodeInputRef fromProto(HighflipMeta.NodeInputProto proto) {
        return com.baidu.highflip.core.entity.dag.common.NodeInputRef
                .builder()
                .setName(proto.getName())
                .setDescription(proto.getDescription())
                .setFromNode(proto.getFromNode())
                .setFromOutput(proto.getFromOutput())
                .build();
    }
}

