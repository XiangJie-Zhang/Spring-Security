
DROP TABLE IF EXISTS "public"."role_info";
CREATE TABLE "public"."role_info" (
  "pk_ri_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "type" int2,
  "delete_status" int2 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."role_info"."pk_ri_id" IS '角色id';
COMMENT ON COLUMN "public"."role_info"."type" IS '角色类型，0：普通，1：管理；2：超级';
COMMENT ON COLUMN "public"."role_info"."delete_status" IS '删除状态0：未删除；1已删除';


-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info";
CREATE TABLE "public"."user_info" (
                                      "pk_ui_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
                                      "create_time" timestamp(6) DEFAULT now(),
                                      "username" varchar(64) COLLATE "pg_catalog"."default",
                                      "time" timestamp(6) DEFAULT now(),
                                      "password" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."user_info"."pk_ui_id" IS '用户id';
COMMENT ON COLUMN "public"."user_info"."username" IS 'name';



-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_role";
CREATE TABLE "public"."user_role" (
  "pk_ur_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(32) COLLATE "pg_catalog"."default",
  "role_id" varchar(32) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."user_role"."pk_ur_id" IS '角色关系对应表id';


-- ----------------------------
-- Primary Key structure for table role_info
-- ----------------------------
ALTER TABLE "public"."role_info" ADD CONSTRAINT "role_info_pkey" PRIMARY KEY ("pk_ri_id");

-- ----------------------------
-- Primary Key structure for table user_info
-- ----------------------------
ALTER TABLE "public"."user_info" ADD CONSTRAINT "user_info_pkey" PRIMARY KEY ("pk_ui_id");

-- ----------------------------
-- Primary Key structure for table user_role
-- ----------------------------
ALTER TABLE "public"."user_role" ADD CONSTRAINT "user_role_pkey" PRIMARY KEY ("pk_ur_id");
