package com.baidu.highflip.core.entity.dag.common;

import com.baidu.highflip.core.utils.ProtoUtils;
import highflip.HighflipMeta;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class NodeOutputRef {

    Integer index;

    String name;

    String description;

    String value;

    public static HighflipMeta.NodeOutputProto toProto(NodeOutputRef ref) {
        HighflipMeta.NodeOutputProto.Builder builder = HighflipMeta.NodeOutputProto
                .newBuilder()
                .setName(ref.getName())
                .setValue(ref.getValue());

        ProtoUtils.setOptional(builder, "Description", ProtoUtils.ofString(ref.getDescription()));
        return builder.build();
    }

    public static NodeOutputRef fromProto(HighflipMeta.NodeOutputProto proto) {
        return NodeOutputRef
                .builder()
                .setName(proto.getName())
                .setDescription(proto.getDescription())
                .setValue(proto.getValue())
                .build();
    }
}
