// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: create_security_card.proto

package newHire.create.security.card;

public final class newHire2ServiceImpl {
  private newHire2ServiceImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_newHire_MessageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_newHire_MessageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_newHire_MessageReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_newHire_MessageReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032create_security_card.proto\022\007newHire\"\036\n" +
      "\016MessageRequest\022\014\n\004text\030\001 \001(\t\"\035\n\014Message" +
      "Reply\022\r\n\005value\030\001 \001(\t2J\n\007newHire\022?\n\013sendM" +
      "essage\022\027.newHire.MessageRequest\032\025.newHir" +
      "e.MessageReply(\001B5\n\034newHire.create.secur" +
      "ity.cardB\023newHire2ServiceImplP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_newHire_MessageRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_newHire_MessageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_newHire_MessageRequest_descriptor,
        new java.lang.String[] { "Text", });
    internal_static_newHire_MessageReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_newHire_MessageReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_newHire_MessageReply_descriptor,
        new java.lang.String[] { "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
