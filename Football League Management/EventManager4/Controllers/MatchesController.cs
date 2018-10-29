using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using EventManager4.Models;

namespace EventManager4.Controllers
{
    public class MatchesController : Controller
    {
        private EventManagementEntitiesEntities db = new EventManagementEntitiesEntities();

        // GET: Matches
        public ActionResult Index()
        {
            var matches = db.Matches.Include(m => m.League).Include(m => m.Team).Include(m => m.Team1);
            if (User.IsInRole("Admin"))
                return View("Index-Admin", matches.ToList());
            return View(matches.ToList());
        }

        // GET: Matches/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Match match = db.Matches.Find(id);
            if (match == null)
            {
                return HttpNotFound();
            }
            return View(match);
        }

        // GET: Matches/Create
        [Authorize(Roles = "Admin")]
        public ActionResult Create()
        {
            ViewBag.LeagueId = new SelectList(db.Leagues, "LeagueId", "LeagueName");
            ViewBag.Team1Id = new SelectList(db.Teams, "TeamId", "TeamName");
            ViewBag.Team2Id = new SelectList(db.Teams, "TeamId", "TeamName");
            return View();
        }

        // POST: Matches/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [Authorize(Roles = "Admin")]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "MatchId,LeagueId,Team1Id,Team2Id,Location,Date")] Match match)
        {
            if (ModelState.IsValid)
            {
                db.Matches.Add(match);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.LeagueId = new SelectList(db.Leagues, "LeagueId", "LeagueName", match.LeagueId);
            ViewBag.Team1Id = new SelectList(db.Teams, "TeamId", "TeamName", match.Team1Id);
            ViewBag.Team2Id = new SelectList(db.Teams, "TeamId", "TeamName", match.Team2Id);
            return View(match);
        }

        // GET: Matches/Edit/5
        [Authorize(Roles = "Admin")]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Match match = db.Matches.Find(id);
            if (match == null)
            {
                return HttpNotFound();
            }
            ViewBag.LeagueId = new SelectList(db.Leagues, "LeagueId", "LeagueName", match.LeagueId);
            ViewBag.Team1Id = new SelectList(db.Teams, "TeamId", "TeamName", match.Team1Id);
            ViewBag.Team2Id = new SelectList(db.Teams, "TeamId", "TeamName", match.Team2Id);
            return View(match);
        }

        // POST: Matches/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [Authorize(Roles = "Admin")]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "MatchId,LeagueId,Team1Id,Team2Id,Location,Date")] Match match)
        {
            if (ModelState.IsValid)
            {
                db.Entry(match).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.LeagueId = new SelectList(db.Leagues, "LeagueId", "LeagueName", match.LeagueId);
            ViewBag.Team1Id = new SelectList(db.Teams, "TeamId", "TeamName", match.Team1Id);
            ViewBag.Team2Id = new SelectList(db.Teams, "TeamId", "TeamName", match.Team2Id);
            return View(match);
        }

        // GET: Matches/Delete/5
        [Authorize(Roles = "Admin")]
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Match match = db.Matches.Find(id);
            if (match == null)
            {
                return HttpNotFound();
            }
            return View(match);
        }

        // POST: Matches/Delete/5
        [Authorize(Roles = "Admin")]
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Match match = db.Matches.Find(id);
            db.Matches.Remove(match);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
