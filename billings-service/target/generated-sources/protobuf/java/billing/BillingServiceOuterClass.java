// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: billing_service.proto

package billing;

public final class BillingServiceOuterClass {
  private BillingServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BillingRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BillingRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BillingResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BillingResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025billing_service.proto\"A\n\016BillingReques" +
      "t\022\022\n\npatient_id\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022\r\n\005e" +
      "mail\030\003 \001(\t\"5\n\017BillingResponse\022\022\n\naccount" +
      "_id\030\001 \001(\t\022\016\n\006status\030\002 \001(\t2D\n\016BillingServ" +
      "ice\0222\n\rCreateBilling\022\017.BillingRequest\032\020." +
      "BillingResponseB\013\n\007billingP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_BillingRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_BillingRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BillingRequest_descriptor,
        new java.lang.String[] { "PatientId", "Name", "Email", });
    internal_static_BillingResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_BillingResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BillingResponse_descriptor,
        new java.lang.String[] { "AccountId", "Status", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
