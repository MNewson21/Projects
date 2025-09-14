let targetDate = null;
let intervalId = null;

function updateCountdown() {
  if (!targetDate) return; 

  const now = new Date();
  const diff = targetDate - now;

  const countdownEl = document.getElementById("countdown");

  if (diff <= 0) {
    countdownEl.textContent = "They didn't bring us here to change the past...";

    clearInterval(intervalId);
    return;
  }

  const days = Math.floor(diff / (1000 * 60 * 60 * 24));
  const hours = Math.floor((diff / (1000 * 60 * 60)) % 24);
  const minutes = Math.floor((diff / (1000 * 60)) % 60);
  const seconds = Math.floor((diff / 1000) % 60);

  countdownEl.textContent = `${days}d ${hours}h ${minutes}m ${seconds}s`;
}

function startCountdown(dateString) {
  // Convert "YYYY-MM-DD" to a Date at midnight
  targetDate = new Date(dateString + "T00:00:00");

  if (intervalId) clearInterval(intervalId); // reset old countdown
  updateCountdown();
  intervalId = setInterval(updateCountdown, 1000);
}

document.addEventListener("DOMContentLoaded", () => {
  const dateInput = document.querySelector("input[type='date']");
  dateInput.addEventListener("change", (e) => {
    startCountdown(e.target.value);
  });
});
