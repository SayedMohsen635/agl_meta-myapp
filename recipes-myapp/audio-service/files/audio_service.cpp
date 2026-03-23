#include <gio/gio.h>
#include <gst/gst.h>
#include <string>

static void play_audio(const char *path) {
  GstElement *pipeline = gst_parse_launch(
      ("filesrc location=" + std::string(path) +
       " ! decodebin ! audioconvert ! audioresample ! alsasink")
          .c_str(),
      nullptr);

  gst_element_set_state(pipeline, GST_STATE_PLAYING);
}

static void handle_method_call(GDBusConnection *connection, const char *sender,
                               const char *object_path,
                               const char *interface_name,
                               const char *method_name, GVariant *parameters,
                               GDBusMethodInvocation *invocation,
                               gpointer user_data) {

  if (g_strcmp0(method_name, "Play") == 0) {
    const char *path;
    g_variant_get(parameters, "(&s)", &path);

    play_audio(path);

    g_dbus_method_invocation_return_value(invocation, nullptr);
  }
}

static const GDBusInterfaceVTable interface_vtable = {handle_method_call,
                                                      nullptr, nullptr};

int main() {
  gst_init(nullptr, nullptr);

  GMainLoop *loop = g_main_loop_new(nullptr, FALSE);

  GDBusNodeInfo *introspection_data = g_dbus_node_info_new_for_xml(
      "<node>"
      "  <interface name='com.sayed.AudioPlayer'>"
      "    <method name='Play'>"
      "      <arg type='s' name='path' direction='in'/>"
      "    </method>"
      "  </interface>"
      "</node>",
      nullptr);

  GDBusConnection *connection =
      g_bus_get_sync(G_BUS_TYPE_SESSION, nullptr, nullptr);

  g_dbus_connection_register_object(
      connection, "/com/sayed/AudioPlayer", introspection_data->interfaces[0],
      &interface_vtable, nullptr, nullptr, nullptr);

  g_bus_own_name(G_BUS_TYPE_SESSION, "com.sayed.AudioPlayer",
                 G_BUS_NAME_OWNER_FLAGS_NONE, nullptr, nullptr, nullptr,
                 nullptr, nullptr);

  g_main_loop_run(loop);
}
