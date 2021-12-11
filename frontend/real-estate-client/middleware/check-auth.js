export default async function (context) {
  await context.store.dispatch("initAuth");
}
