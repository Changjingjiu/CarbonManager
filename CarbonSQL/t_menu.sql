INSERT INTO `t_menu` (`menu_id`, `component`, `path`, `redirect`, `name`, `title`, `icon`, `parent_id`, `is_leaf`, `hidden`) VALUES (1, 'Layout', '/user', '/user/list', 'userManage', '用户管理', 'userManage', 0, 'N', 0);
INSERT INTO `t_menu` (`menu_id`, `component`, `path`, `redirect`, `name`, `title`, `icon`, `parent_id`, `is_leaf`, `hidden`) VALUES (2, 'user/user', 'list', NULL, 'userList', '用户列表', 'userList', 1, 'Y', 0);
INSERT INTO `t_menu` (`menu_id`, `component`, `path`, `redirect`, `name`, `title`, `icon`, `parent_id`, `is_leaf`, `hidden`) VALUES (3, 'user/role', 'role', NULL, 'roleList', '角色列表', 'role', 1, 'Y', 0);
INSERT INTO `t_menu` (`menu_id`, `component`, `path`, `redirect`, `name`, `title`, `icon`, `parent_id`, `is_leaf`, `hidden`) VALUES (4, 'user/permission', 'permission', NULL, 'permissionList', '权限列表', 'permission', 1, 'Y', 0);