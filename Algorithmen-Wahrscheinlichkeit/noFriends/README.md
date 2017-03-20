# Is graph bipartite?

You decided to organise a dinner party at your home. Since you are an outgoing person your friend
pool is very large, but unfortunately not all of your friends are acquainted with each other. In
order for the evening to be successful and entertaining for everybody you make the following plan.
For each guest, you write down the list of people she is a friend with. You know that the friendships
are a symmetric relation, i.e. that if a person a is a friend of a person b then the person b is a
friend of the person a.
Your plan is simple: you want to place each guest on one of the two sides of your table and
furthermore, you want to place all her friends on the other side of the table, exactly across, so that
the conversations are flowing smoothly. You are sure that your table is large enough, but from a
first glance at your friendship lists you cannot deduce whether such a placement is possible.
Luckily, you are a fairly good programmer and decide to rely on your programming skills to check
if your plan for the evening can happen. Moreover, if your seating plan can work out, you want to
know who will be seated on the same side of the table as your best friend.

If the given graph is bipartite, it prints the nodes with the same color as the given input-node. Same color means the nodes are not connected and belong to one part of the bipartite graph.
